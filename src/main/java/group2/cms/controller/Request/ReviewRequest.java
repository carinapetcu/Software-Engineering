package group2.cms.controller.Request;

import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import lombok.*;

import java.io.File;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewRequest implements Serializable {
    private Long reviewID;
    private PCMember pcMember;
    private Paper paper;
    private File feedback;
    private Integer result;
}
