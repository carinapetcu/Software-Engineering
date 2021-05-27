package group2.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Conference extends BaseEntity<Long> {

    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description; // TODO: check how string is put into SQL

    @OneToOne(cascade = CascadeType.ALL)
    private PCMember chair;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PCMember> coChairs;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PCMember> pcMembers;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Section> sections;
}
