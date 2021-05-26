package group2.cms.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PCMember extends BaseEntity<Long> {
    private String affiliation;
    private String webPage;
    private int maxPapersToReview = 0;
    @OneToOne
    private Author author;

    public PCMember(Author author, String affiliation) {
        this.author.getUser().setAuthority(Authority.PCMember);
        this.affiliation = affiliation;
    }

}
