package group2.cms.service.DTO.Paper;

import group2.cms.service.DTO.EntityDTO;
import group2.cms.domain.Paper;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class PaperResponse extends EntityDTO<Paper> {
    private final String title;
    private final String paperAbstract;
}
