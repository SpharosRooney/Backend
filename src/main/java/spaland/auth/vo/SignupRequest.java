package spaland.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {

    private String userNickname;
    private String userEmail;
    private String userName;
    private String password;
    private String phone;


}
