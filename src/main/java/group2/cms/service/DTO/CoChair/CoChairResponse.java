package group2.cms.service.DTO.CoChair;

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
public class CoChairResponse extends EntityDTO<PCMember>{
    private String fullName;
    private String email;
    private String affiliation;
    private String webPage;
}
