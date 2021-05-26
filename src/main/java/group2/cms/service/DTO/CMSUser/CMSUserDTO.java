package group2.cms.service.DTO.CMSUser;


import group2.cms.domain.CMSUser;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Getter
public class CMSUserDTO extends EntityDTO<CMSUser> {
    private final Long id;
    private final String fullName;
    private final String username;
    private final String email;
    private final String password;
}
