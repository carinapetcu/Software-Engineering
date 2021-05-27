package group2.cms.service;

import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import group2.cms.domain.Review;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.repository.*;
import group2.cms.service.DTO.Paper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private PCMemberRepository pcMemberRepo;

    public PapersToReviewResponse getPapersForReview(Long userId) {
        var res = new PapersToReviewResponse();
        reviewRepo.findAll()
                .stream()
                .filter(
                        r -> Objects.equals(r.getPcMember()
                                .getAuthor()
                                .getUser()
                                .getId(), userId) && r.getResult() == null
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

    public void shufflePapers(Long conferenceId) {
        var conference = conferenceRepo.findById(conferenceId)
                .orElseThrow(() -> new InvalidIDException("Conference with given id doesn't exist."));
        var papers = new ArrayList<>(conference.getPapers());

        var reviewers = conference.getPcMembers()
                .stream()
                .filter(pm -> pm.getMaxPapersToReview() > 0)
                .collect(Collectors.toList());
        for (PCMember reviewer : reviewers) {
            if (papers.size() == 0) {
                break;
            }
            var max = reviewer.getMaxPapersToReview();
            while (max > 0) {
                var index = (int) (Math.random() * (papers.size() - 1));
                var review = new Review();
                review.setPcMember(reviewer);
                review.setPaper(conference.getPapers()
                        .stream()
                        .filter(p -> Objects.equals(p.getId(), papers.get(index).getId()))
                        .collect(Collectors.toList())
                        .get(0));
                try {
                    reviewRepo.save(review);
                } catch (Exception e) {
                    throw new ServerException(e.getMessage());
                }
                papers.remove(index);
                max--;
            }
        }
    }

    public void setPapersToReview(PaperToReviewCountRequest req) {
        var pm = pcMemberRepo.findById(req.getUserId())
                .orElseThrow(() -> new InvalidIDException("User with given id not found."));

        pm.setMaxPapersToReview(req.getNoOfPapersToReview());
        try {
            pcMemberRepo.save(pm);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public IsPaperReviewableResponse isPaperReviewable(Long paperId, Long userId) {
        var res = new IsPaperReviewableResponse();
        boolean exists = false;

        try {
            for (Review r : reviewRepo.findAll()) {
                if (Objects.equals(r.getPaper().getId(), paperId) &&
                        Objects.equals(r.getPcMember()
                                .getAuthor()
                                .getUser()
                                .getId(), userId)) {
                    exists = true;
                    res.setCanReview(r.getResult() == null);
                    break;
                }
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        if (!exists) {
            throw new InvalidIDException("Paper or user not found.");
        }

        return res;
    }
}
