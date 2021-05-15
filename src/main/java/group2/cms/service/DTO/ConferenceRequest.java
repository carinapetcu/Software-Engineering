package group2.cms.service.DTO;

import group2.cms.domain.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Builder
public class ConferenceRequest extends BaseEntity<Integer> {
    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;
}
