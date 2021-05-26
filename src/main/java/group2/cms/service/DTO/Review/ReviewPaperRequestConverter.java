package group2.cms.service.DTO.Review;

import group2.cms.domain.CMSUser;
import group2.cms.domain.Review;
import group2.cms.domain.PCMember;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;
import group2.cms.service.DTO.SignUp.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewPaperRequestConverter {
    public Review dtoToEntity(ReviewPaperRequest dto) {
        var review = new Review(
                dto.getUserId(),
                null,
                dto.getPaperId(),
                dto.getFeedback(),
                dto.getResult()
        );
        review.setId(null);
        return review;
    }
}
