package group2.cms.service.DTO.Paper;
import lombok.*;

@Builder
@Data
@Getter
public class GetPaperResponse{
    private final String title;
    private final String abstract;
}
