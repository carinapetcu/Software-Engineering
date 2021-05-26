package group2.cms.service.DTO.Login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private Long userID;
    private Integer authority;
    private Long conferenceID;
}
