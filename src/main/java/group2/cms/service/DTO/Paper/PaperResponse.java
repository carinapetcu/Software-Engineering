package group2.cms.service.DTO.Paper;
import lombok.*;
import java.util.List;

@Builder
@Data
@Getter
public class PaperResponse {
    private final List<PaperDTO> papers;
}
