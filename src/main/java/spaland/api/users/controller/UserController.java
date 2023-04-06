package spaland.api.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.users.service.IUserService;
import spaland.api.users.vo.LoginRequest;
import spaland.api.users.vo.LoginResponse;
import spaland.api.users.vo.SignupRequest;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService iUserService;



    @PostMapping("/signup")
    public ResponseEntity<Message> signup(
            @RequestBody SignupRequest signupRequest) {
        return iUserService.singup(signupRequest);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Message> getUser(@PathVariable(value = "id") Long id){
        log.info("input id ? {}",id);
        return iUserService.getUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = iUserService.login(loginRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, loginResponse.getToken());
        return ResponseEntity.ok().headers(headers).body(loginResponse.getUserNickname());

    }


    @GetMapping("/logout")
    public void logout(@RequestHeader(value = "Authorization") String accessToken) {
        iUserService.logout(accessToken);
        accessToken = null;


    }
}

