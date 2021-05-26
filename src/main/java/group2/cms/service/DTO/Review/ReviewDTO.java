package group2.cms.service.DTO.Review;

import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import group2.cms.domain.Review;
import group2.cms.service.DTO.EntityDTO;
import java.io.File;
import lombok.*;


@Builder(toBuilder = true)

@Getter
public class ReviewDTO extends EntityDTO<Review> {

    private Long id;
    private PCMember pcMember;
    private Paper paper;
    private File feedback;
    private Integer result;
}
