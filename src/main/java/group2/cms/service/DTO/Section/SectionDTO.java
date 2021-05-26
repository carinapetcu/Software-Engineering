package group2.cms.service.DTO.Section;

import group2.cms.domain.Section;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class SectionDTO extends EntityDTO<Section> {
    private final Long id;
    private final String room;
    private final Integer capacity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String theme;
    private final Long pcMemberId;
}
