package group2.cms.domain;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity<Serializable> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private PCMember pcMember;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Paper paper;

    private File feedback;

    private Integer result;
}
