package group2.cms.service.DTO;


import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CMSUserRequest {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;
}
