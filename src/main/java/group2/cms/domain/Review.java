package group2.cms.domain;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@EqualsAndHashCode
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
