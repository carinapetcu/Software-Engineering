package group2.cms.service.DTO.Review;

import group2.cms.domain.PCMember;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.domain.Review;
import group2.cms.service.DTO.EntitiesDTO;
import group2.cms.domain.Paper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ReviewDTOConverter implements DTOConverter<Review, ReviewDTO> {
    @Override
    public Review dtoToEntity(ReviewDTO dto) {
        var pcMember = new PCMember();
        pcMember.setId(dto.getPcMemberID());

        var paper = new Paper();
        paper.setId(dto.getPaperID());

        var review = new Review(
            pcMember,
                paper,
                dto.getFeedback(),
                dto.getResult()
        );
        
        if(dto.getReviewID() != null)
            review.setId(dto.getReviewID());
        return review;
    }

    @Override
    public ReviewDTO entityToDto(Review review) {
        return ReviewDTO.builder()
                .reviewID(review.getId())
                .pcMemberID(review.getPcMember().getId())
                .paperID(review.getPaper().getId())
                .feedback(review.getFeedback())
                .result(review.getResult())
                .build();
    }

    @Override
    public ReviewsDTO entitiesToDTO(Collection<Review> reviews) {
        var reviewsDTO = new ReviewsDTO();
        reviews.stream()
                .map(this::entityToDto)
                .forEach(reviewsDTO::addDTO);
        return reviewsDTO;
    }
}
