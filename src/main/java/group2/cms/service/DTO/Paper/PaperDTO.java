package group2.cms.service.DTO.Paper;

import group2.cms.domain.Paper;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class PaperDTO extends EntityDTO<Paper> {
    private final Long id;
    private final String title;
    private final String paperAbstract;

    @Singular
    private final List<Long> authorIDs;

    @Singular
    private final List<String> keywords;
}
