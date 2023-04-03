package spaland.users.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import spaland.Response.Message;
import spaland.users.model.User;
import spaland.users.vo.*;

public interface IUserService {

    ResponseEntity<Message> singup(SignupRequest signupRequest);
    ResponseEntity<Message> getUser(Long id);
    LoginResponse login(LoginRequest loginRequest);
    void logout(String access);

    boolean checkDuplicateId(String userId);
}
