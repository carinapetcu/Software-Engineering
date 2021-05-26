package group2.cms.service.DTO.Presentation;


import group2.cms.domain.Paper;
import group2.cms.domain.Section;
import group2.cms.service.DTO.EntityDTO;
import group2.cms.domain.Presentation;
import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;


@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class PresentationDTO extends EntityDTO<Presentation> implements Serializable {
    private final Long id;
    private final Paper paper;
    private final Section section;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final File presentation;
}
