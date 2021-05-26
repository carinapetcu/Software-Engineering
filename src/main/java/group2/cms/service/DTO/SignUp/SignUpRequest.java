package group2.cms.service.DTO.SignUp;

import lombok.*;

@Builder
@Data
@Getter
public class SignUpRequest {
    private final String fullName;
    private final String username;
    private final String email;
    private final String password;
}
