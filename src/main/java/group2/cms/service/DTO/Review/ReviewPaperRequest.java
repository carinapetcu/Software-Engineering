package group2.cms.service.DTO.Review;

import group2.cms.domain.Paper;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.File;

@Builder
@Data
@Getter
public class ReviewPaperRequest {
    private Long userId;
    private Paper paperId;
    private File feedback;
    private Integer result;
}
