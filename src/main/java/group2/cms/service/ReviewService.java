package group2.cms.service;

import group2.cms.domain.Author;
import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import group2.cms.domain.Review;
import group2.cms.exceptions.BackendException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.ReviewRepository;
import group2.cms.service.DTO.Review.ReviewDTO;
import group2.cms.service.DTO.Review.ReviewDTOConverter;
import group2.cms.service.DTO.Review.ReviewsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PCMemberRepository pcMemberRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ReviewDTOConverter converter;

    @Autowired
    private EmailService emailService;

    @Data
    @Builder
    private class FeedbackData {
        private String authorEmail;
        private String paperTitle;
        private Integer reviewResult;
        private String feedback;
    }

    public ReviewDTO addReview(ReviewDTO dto) {
        var review = converter.dtoToEntity(dto);
        var member = pcMemberRepository
                .findById(dto.getPcMemberID())
                .orElseThrow(() -> new BackendException("PC Member not found"));
        review.setPcMember(member);

        var paper = paperRepository
                .findById(dto.getPaperID())
                .orElseThrow(() -> new BackendException("Paper not found"));
        review.setPaper(paper);
        var result = reviewRepository.save(review);

        paper.getAuthorList()
                .stream()
                .map((Author author) ->
                        FeedbackData.builder()
                                .authorEmail(author.getEmail())
                                .paperTitle(paper.getTitle())
                                .reviewResult(review.getResult())
                                .feedback(review.getFeedback())
                                .build())
                .forEach(this::sendFeedbackToAuthor);

        return converter.entityToDto(result);
    }

    public void deleteReview(Long reviewID) {
        try {
            reviewRepository.deleteById(reviewID);
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            throw new InvalidIDException("Invalid Review ID: " + reviewID);
        }
    }

    public ReviewsDTO getAllReviews() {
        return converter.entitiesToDTO(reviewRepository.findAll());
    }

    public ReviewDTO getReviewByID(Long reviewID) {
        return converter.entityToDto(reviewRepository
                .findById(reviewID)
                .orElseThrow(
                        () -> new InvalidIDException("Invalid Presentation ID: " + reviewID)
                ));
    }
}
