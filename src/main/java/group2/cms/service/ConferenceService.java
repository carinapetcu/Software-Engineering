package group2.cms.service;

import group2.cms.domain.*;
import group2.cms.exceptions.InvalidCredentialsException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.repository.*;
import group2.cms.service.DTO.CoChair.CoChairResponse;
import group2.cms.service.DTO.CoChair.CoChairsResponse;
import group2.cms.service.DTO.Conference.ConferenceRequest;
import group2.cms.service.DTO.Conference.ConferenceResponse;
import group2.cms.service.DTO.Conference.ConferencesResponse;
import group2.cms.service.DTO.PCMember.PCMemberResponse;
import group2.cms.service.DTO.PCMember.PCMembersResponse;
import group2.cms.service.DTO.PCMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepo;

    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PCMemberRepository pcMemberRepo;

    @Autowired
    private AuthorRepository authorRepo;

    public Long addConference(ConferenceRequest data) {
        var newConference = new Conference();
        newConference.setName(data.getName());
        newConference.setEdition(data.getEdition());
        newConference.setDescription(data.getDescription());
        newConference.setStartDate(data.getStartDate());
        newConference.setEndDate(data.getEndDate());

        var user = getUserById(data.getUserId());
        user.setAuthority(Authority.Chair);
        var author = new Author();
        author.setUser(user);
        var chair = new PCMember();
        chair.setAuthor(author);
        chair.setAffiliation("");
        chair.setWebPage("");

        newConference.setChair(chair);

        try {
            var conf = conferenceRepo.save(newConference);
            return conf.getId();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }


    public ConferenceResponse getById(Long conferenceID) {
        var conference = getConferenceById(conferenceID);
        return ConferenceResponse.builder()
                .id(conferenceID)
                .edition(conference.getEdition())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .name(conference.getName())
                .description(conference.getDescription())
                .build();
    }

    public ConferencesResponse getAll() {
        try {
            var res = new ConferencesResponse();
            conferenceRepo.findAll()
                    .forEach((c) -> res.addDTO(
                            ConferenceResponse.builder()
                                    .id(c.getId())
                                    .edition(c.getEdition())
                                    .startDate(c.getStartDate())
                                    .endDate(c.getEndDate())
                                    .name(c.getName())
                                    .description(c.getDescription())
                                    .build()
                    ));
            return res;
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public CoChairsResponse getCoChairsFromConference(Long conferenceId) {
        var conference = getConferenceById(conferenceId);
        var coChairs = new CoChairsResponse();

        conference.getCoChairs()
                .forEach((cc) -> {
                    var user = cc.getAuthor().getUser();
                    coChairs.addDTO(CoChairResponse.builder()
                            .affiliation(cc.getAffiliation())
                            .email(user.getEmail())
                            .fullName(user.getFullName())
                            .webPage(cc.getWebPage())
                            .build()
                    );
                });

        return coChairs;
    }

    public PCMembersResponse getPCMembersFromConference(Long conferenceId) {
        var conference = getConferenceById(conferenceId);

        var pcMembers = new PCMembersResponse();
        conference.getPcMembers()
                .forEach((pm) -> {
                    var user = pm.getAuthor().getUser();
                    AtomicBoolean hasPaper = new AtomicBoolean(false);
                    paperRepo.findAll().forEach((p) -> {
                        p.getAuthorList().forEach((a) -> {
                            if (a == pm.getAuthor()) {
                                hasPaper.set(true);
                            }
                        });
                    });
                    pcMembers.addDTO(PCMemberResponse.builder()
                            .affiliation(pm.getAffiliation())
                            .hasPaper(hasPaper.get())
                            .email(user.getEmail())
                            .fullName(user.getFullName())
                            .webPage(pm.getWebPage())
                            .build());
                });
        return pcMembers;
    }

    public PCMembersResponse getReviewersFromConference(Long conferenceId) {
        var conference = conferenceRepo.findById(conferenceId).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceId)
        );

        var pcMembers = new PCMembersResponse();
        conference.getPcMembers()
                .stream()
                .filter((pm) -> pm.getMaxPapersToReview() > 0)
                .forEach((pm) -> {
                    var user = pm.getAuthor().getUser();
                    AtomicBoolean hasPaper = new AtomicBoolean(false);
                    paperRepo.findAll().forEach((p) -> {
                        p.getAuthorList().forEach((a) -> {
                            if (a == pm.getAuthor()) {
                                hasPaper.set(true);
                            }
                        });
                    });
                    pcMembers.addDTO(PCMemberResponse.builder()
                            .affiliation(pm.getAffiliation())
                            .hasPaper(hasPaper.get())
                            .email(user.getEmail())
                            .fullName(user.getFullName())
                            .webPage(pm.getWebPage())
                            .build());
                });
        return pcMembers;
    }


    public void addCoChairToConference(Long id, PCMemberRequest req) {
        var conference = getConferenceById(id);

        var user = getUserByEmail(req.getEmail());

        var coChair = createPcMember(user, req, Authority.CoChair);

        if (conference.getCoChairs() == null) {
            conference.setCoChairs(new HashSet<>());
        }
        conference.getCoChairs().add(coChair);
        try {
            conferenceRepo.save(conference);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public void addPCMemberToConference(Long id, PCMemberRequest req) {
        var conference = getConferenceById(id);

        var user = getUserByEmail(req.getEmail());

        var pcMember = createPcMember(user, req, Authority.PCMember);
        if (conference.getPcMembers() == null) {
            conference.setPcMembers(new HashSet<>());
        }
        conference.getPcMembers().add(pcMember);
        try {
            conferenceRepo.save(conference);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    private Conference getConferenceById(Long id) {
        return conferenceRepo.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + id)
        );
    }

    private CMSUser getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new InvalidCredentialsException("User with given email not found.")
                );
    }

    private CMSUser getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(
                        () -> new InvalidIDException("Invalid ID: " + userId)
                );
    }

    private PCMember createPcMember(CMSUser user, PCMemberRequest req, Authority authority) {
        var author = new Author();
        author.setUser(user);
        var pcMember = new PCMember();
        pcMember.setAffiliation(req.getAffiliation());
        pcMember.setAuthor(author);
        pcMember.setMaxPapersToReview(0);
        pcMember.setWebPage(req.getWebsite());
        user.setAuthority(authority);

        return pcMember;
    }
}
