package group2.cms.service.DTO.PCMember;

import group2.cms.domain.PCMember;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class PCMemberResponse extends EntityDTO<PCMember> {
    private String fullName;
    private String email;
    private String affiliation;
    private String webPage;
    private boolean hasPaper;
}
