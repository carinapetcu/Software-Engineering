package group2.cms.domain;

import lombok.*;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@EqualsAndHashCode
public class Review extends BaseEntity<Long>{
    @ManyToOne
    @PrimaryKeyJoinColumn
    private PCMember pcMember;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Paper paper;

    private String feedback;

    private Integer result;
}
