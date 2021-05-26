package group2.cms.service.DTO.Login;

import lombok.*;

@Builder
@Data
@Getter
public class LoginRequest {
    private final String username;
    private final String password;
}
