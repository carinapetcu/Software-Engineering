package group2.cms.service.DTO.Review;
import lombok.*;

@Builder
@Data
@Getter
public class IsReviewableRequest {
    private final long userId;
    private final long paperId;
}
