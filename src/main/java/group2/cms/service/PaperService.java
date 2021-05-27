package group2.cms.service;

import group2.cms.domain.Review;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.ReviewRepository;
import group2.cms.service.DTO.Paper.PaperToReviewResponse;
import group2.cms.service.DTO.Paper.PapersToReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import group2.cms.service.DTO.Paper.PaperResponse;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.Paper.PaperRequest;
import group2.cms.repository.AuthorRepository;
import group2.cms.exceptions.ServerException;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class PaperService {
    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private AuthorRepository authorRepo;

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
        try {
            paperRepo.save(paper);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
