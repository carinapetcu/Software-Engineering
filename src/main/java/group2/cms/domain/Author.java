package group2.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Author extends BaseEntity<Long> {
    @ManyToOne
    protected Paper paper;

    @OneToOne
    private CMSUser user;

    public Author(CMSUser user) {
        this.user = user;
        this.user.setAuthority(Authority.Author);
    }
}
