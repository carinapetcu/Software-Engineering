package group2.cms.service.DTO.Conference;

import group2.cms.domain.Conference;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Builder(toBuilder = true)
public class ConferenceRequest extends EntityDTO<Conference> {
    private final Long userId;
    private final String name;
    private final String edition;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
