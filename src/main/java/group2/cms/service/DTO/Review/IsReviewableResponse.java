package group2.cms.service.DTO.Review;
import lombok.*;

@Builder
@Data
@Getter
public class IsReviewableResponse {
    private final boolean canReview;
}
