package group2.cms.controller.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class CoChairRequest implements Serializable {
    private Long userID;
    private String affiliation;
    private String website;
}
