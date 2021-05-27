package group2.cms.service.DTO.Paper;

import group2.cms.domain.Author;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class PaperWithAuthorsResponse extends EntityDTO<Paper> {
    private final Long id;
    private final String title;
    private final List<Author> authors;
}
