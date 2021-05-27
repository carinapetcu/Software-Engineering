package group2.cms.service.DTO.Paper;

import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IsPaperReviewableResponse implements Serializable {
    private Boolean canReview;
}
