package group2.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @ToString
public class Conference extends BaseEntity<Long>{

    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description; // TODO: check how string is put into SQL

    @OneToOne
    private PCMember chair;

    @OneToMany
    private Set<PCMember> coChairs;

    @OneToMany
    private Set<PCMember> pcMembers;

    @OneToMany
    private Set<Section> sections;
    //private List<Date> deadlines; TODO: figure out how to represent this
}
