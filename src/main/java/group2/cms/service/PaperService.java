package group2.cms.service;

import group2.cms.domain.Review;
import group2.cms.repository.ConferenceRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.ReviewRepository;
import group2.cms.service.DTO.Paper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.domain.Paper;
import group2.cms.repository.AuthorRepository;
import group2.cms.exceptions.ServerException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Service
public class PaperService {
    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private ConferenceRepository conferenceRepo;

    public PapersToReviewResponse getPapersForReview(Long userId) {
        var res = new PapersToReviewResponse();
        reviewRepo.findAll()
                .stream()
                .filter(
                        r -> Objects.equals(r.getPcMember()
                                .getAuthor()
                                .getUser()
                                .getId(), userId)
                )
                .map((Review::getPaper))
                .map(p -> PaperToReviewResponse.builder()
                        .authors(p.getAuthorList())
                        .paperAbstract(p.getPaperAbstract())
                        .paperId(p.getId())
                        .title(p.getTitle())
                        .build())
                .forEach(res::addDTO);
        return res;
    }

    public PaperResponse getPaper(Long id) {
        var paper = paperRepo.findById(id)
                .orElseThrow(() -> new InvalidIDException("Paper with given id doesn't exits."));
        return PaperResponse.builder()
                .paperAbstract(paper.getPaperAbstract())
                .title(paper.getTitle())
                .build();
    }

    public void addPaperToConference(Long confId, PaperRequest data) {
        var conference = conferenceRepo.findById(confId)
                .orElseThrow(() -> new InvalidIDException("Conference with given id doesn't exist."));
        if (conference.getPapers() == null) {
            conference.setPapers(new HashSet<>());
        }
        var paper = new Paper();
        paper.setTitle(data.getTitle());
        paper.setPaperAbstract(data.getPaperAbstract());
        paper.setAuthorList(new ArrayList<>());
        data.getAuthorEmails()
                .forEach(
                        (email) -> {
                            authorRepo.findAll()
                                    .stream()
                                    .filter(
                                            a -> Objects.equals(a.getUser().getEmail(), email))
                                    .forEach(
                                            author -> paper.getAuthorList()
                                                    .add(author)
                                    );
                        }
                );
        conference.getPapers().add(paper);
        try {
            conferenceRepo.save(conference);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }


    public PapersWithAuthorsResponse getPapersFromConference(Long conferenceId) {
        var conference = conferenceRepo.findById(conferenceId)
                .orElseThrow(() -> new InvalidIDException("Conference with given id doesn't exist."));
        var res = new PapersWithAuthorsResponse();
        conference.getPapers()
                .stream()
                .map(paper -> PaperWithAuthorsResponse.builder()
                        .id(paper.getId())
                        .title(paper.getTitle())
                        .authors(paper.getAuthorList())
                        .build())
                .forEach(res::addDTO);

        return res;
    }
}
