package group2.cms.controller.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class CoChairRequest {
    private Long userID;
    private String affiliation;
    private String website;
}
