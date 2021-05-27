package group2.cms.service.DTO.Paper;

import group2.cms.domain.Paper;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class PaperRequest extends EntityDTO<Paper> {
    private final String title;
    private final List<String> authorEmails;
    private final String paperAbstract;
}
