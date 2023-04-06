package spaland.api.users.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.users.vo.LoginRequest;
import spaland.api.users.vo.LoginResponse;
import spaland.api.users.vo.SignupRequest;


public interface IUserService {

    ResponseEntity<Message> singup(SignupRequest signupRequest);
    ResponseEntity<Message> getUser(Long id);
    LoginResponse login(LoginRequest loginRequest);
    void logout(String access);

}
