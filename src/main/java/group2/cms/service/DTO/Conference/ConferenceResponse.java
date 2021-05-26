package group2.cms.service.DTO.Conference;

import group2.cms.domain.Conference;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Builder(toBuilder = true)
public class ConferenceResponse extends EntityDTO<Conference> {
    private final Long id;
    private final String name;
    private final String edition;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
