package group2.cms.service.DTO;

import group2.cms.domain.PCMember;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class PCMemberRequest extends EntityDTO<PCMember> {
    private final String email;
    private final String affiliation;
    private final String website;
}
