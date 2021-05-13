package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
// @AllArgsConstructor
@EqualsAndHashCode
// @ToString
public class Conference {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;

    //private List<Date> deadlines; TODO: figure out how to represent this
}
