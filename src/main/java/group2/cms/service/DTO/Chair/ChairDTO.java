package group2.cms.service.DTO.Chair;

import group2.cms.domain.Chair;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class ChairDTO extends EntityDTO<Chair> {
    private final Long userID;
    private final String fullName;
    private final String username;
    private final String email;
    private final String password;
    private final Long paperID;
    private final String affiliation;
    private final String webPage;
}
