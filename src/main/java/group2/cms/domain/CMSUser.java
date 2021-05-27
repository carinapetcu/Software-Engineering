package group2.cms.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CMSUser extends BaseEntity<Long> {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Authority authority = null;
}
