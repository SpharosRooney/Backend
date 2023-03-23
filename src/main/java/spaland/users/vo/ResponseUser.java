package spaland.users.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {

    private String userNickname;
    private String userEmail;
    private String userName;
    private String password;
    private String phone;

}
