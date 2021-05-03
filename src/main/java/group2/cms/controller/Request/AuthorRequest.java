package group2.cms.controller.Request;

import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorRequest implements Serializable {
    private Long userID;
    private Long paperID;
}
