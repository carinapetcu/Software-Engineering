package group2.cms.service.DTO.Paper;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PaperToReviewCountRequest implements Serializable {
    private final Long userId;
    private final Integer noOfPapersToReview;
}
