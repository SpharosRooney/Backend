package spaland.cart.model;

import lombok.Getter;

@Getter
public class Auth {
    private Long id;
    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userName;
    private String password;
    private String phone;
    private String role;
}
