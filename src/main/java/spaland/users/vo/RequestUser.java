package spaland.users.vo;

import lombok.Data;

@Data
public class RequestUser {

    private String userNickname;
    private String userEmail;
    private String userName;
    private String password;
    private String phone;

}
