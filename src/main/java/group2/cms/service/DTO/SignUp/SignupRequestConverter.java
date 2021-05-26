package group2.cms.service.DTO.SignUp;

import group2.cms.domain.CMSUser;
import group2.cms.service.DTO.CMSUser.CMSUserDTO;
import group2.cms.service.DTO.CMSUser.CMSUsersDTO;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SignupRequestConverter {
    public CMSUser dtoToEntity(SignUpRequest dto) {
        var user = new CMSUser(
                dto.getFullName(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword(),
                null
        );
        user.setId(null);
        return user;
    }
}
