package group2.cms.service.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PaperRequest implements Serializable {
    private String title;
    private String paperAbstract;
    private List<Long> authorList;
    private Set<String> keywordSet;
}
