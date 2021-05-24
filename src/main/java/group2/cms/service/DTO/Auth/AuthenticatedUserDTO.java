package group2.cms.service.DTO.Auth;

import group2.cms.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthenticatedUserDTO {
    private String userName;
    private Authority authority;
}
