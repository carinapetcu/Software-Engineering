package group2.cms.service.DTO.Review;

import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;
import group2.cms.domain.Review;

import java.io.File;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
public class ReviewDTO extends EntityDTO<Review> {
    private Long reviewID;
    private Long pcMemberID;
    private Long paperID;
    private String feedback;
    private Integer result;
}
