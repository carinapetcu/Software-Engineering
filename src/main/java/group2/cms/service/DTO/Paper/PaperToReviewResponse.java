package group2.cms.service.DTO.Paper;

import group2.cms.service.DTO.EntityDTO;
import group2.cms.domain.Paper;
import group2.cms.domain.Author;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class PaperToReviewResponse extends EntityDTO<Paper> {
    private final Long paperId;
    private final String title;
    private final String paperAbstract;
    private List<Author> authors;
}
