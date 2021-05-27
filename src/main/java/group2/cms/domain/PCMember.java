package group2.cms.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PCMember extends BaseEntity<Long> {
    private String affiliation;
    private String webPage;
    private int maxPapersToReview = 0;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Author author;

    public PCMember(Author author, String affiliation) {
        this.author.getUser().setAuthority(Authority.PCMember);
        this.affiliation = affiliation;
    }

}
