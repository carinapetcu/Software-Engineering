package group2.cms.service;

import group2.cms.domain.Author;
import group2.cms.domain.Authority;
import group2.cms.domain.Conference;
import group2.cms.domain.PCMember;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.repository.ConferenceRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.CoChair.CoChairResponse;
import group2.cms.service.DTO.CoChair.CoChairsResponse;
import group2.cms.service.DTO.Conference.ConferenceRequest;
import group2.cms.service.DTO.Conference.ConferenceResponse;
import group2.cms.service.DTO.Conference.ConferenceDTOConverter;
import group2.cms.service.DTO.Conference.ConferencesResponse;
import group2.cms.service.DTO.PCMember.PCMemberResponse;
import group2.cms.service.DTO.PCMember.PCMembersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ConferenceService {
    @Autowired
    private ConferenceDTOConverter conferenceDTOConverter;

    @Autowired
    private ConferenceRepository conferenceRepo;

    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private UserRepository userRepo;

    public Long addConference(ConferenceRequest data) {
        var newConference = new Conference();
        newConference.setName(data.getName());
        newConference.setEdition(data.getEdition());
        newConference.setDescription(data.getDescription());
        newConference.setStartDate(data.getStartDate());
        newConference.setEndDate(data.getEndDate());

        var user = userRepo.findById(data.getUserId())
                .orElseThrow(() -> new InvalidIDException("User does not exist."));

        var author = new Author();
        author.setUser(user);
        var chair = new PCMember();
        chair.setAuthor(author);
        chair.setAffiliation("");
        chair.setWebPage("");
        user.setAuthority(Authority.Chair);

        newConference.setChair(chair);

        newConference.setCoChairs(new HashSet<>());
        newConference.setPcMembers(new HashSet<>());
        newConference.setSections(new HashSet<>());
        try {
            var conf = conferenceRepo.save(newConference);
            return conf.getId();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public void deleteConference(Long conferenceID) {
        try {
            conferenceRepo.deleteById(conferenceID);
        } catch (EmptyResultDataAccessException | IllegalArgumentException e) {
            throw new InvalidIDException("Invalid conferenceID: " + conferenceID);
        }
    }

    public ConferenceResponse updateConference(ConferenceResponse conferenceResponse) {
        var conferenceData = conferenceDTOConverter.dtoToEntity(conferenceResponse);
        var id = conferenceData.getId();
        var conf = conferenceRepo.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID:" + id)
        );
        var name = conferenceData.getName();
        var edition = conferenceData.getEdition();
        var startDate = conferenceData.getStartDate();
        var endDate = conferenceData.getEndDate();

        if (name != null) conf.setName(name);
        if (edition != null) conf.setEdition(edition);
        if (startDate != null) conf.setStartDate(startDate);
        if (endDate != null) conf.setEndDate(endDate);

        return conferenceDTOConverter.entityToDto(conferenceRepo.save(conf));
    }

    public ConferenceResponse getById(Long conferenceID) {
        var conference = conferenceRepo.findById(conferenceID).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceID)
        );
        return conferenceDTOConverter.entityToDto(conference);
    }

    public ConferencesResponse getAll() {
        try {
            return conferenceDTOConverter.entitiesToDTO(conferenceRepo.findAll());
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public CoChairsResponse getCoChairsFromConference(Long conferenceId) {
        var conference = conferenceRepo.findById(conferenceId).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceId)
        );
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
        var conference = conferenceRepo.findById(conferenceId).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceId)
        );

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
}
