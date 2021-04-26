package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PCMember extends Author{
    private String affiliation;
    private String webPage;
}
