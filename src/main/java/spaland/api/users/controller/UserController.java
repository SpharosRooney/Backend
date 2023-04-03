package spaland.api.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.users.service.IUserService;
import spaland.api.users.service.UserServiceImple;
import spaland.api.users.vo.LoginRequest;
import spaland.api.users.vo.LoginResponse;
import spaland.api.users.vo.SignupRequest;
import spaland.zincomplete.auth.service.AuthenticationService;
import spaland.config.JwtService;
import spaland.api.email.service.RedisService;
import spaland.utility.CookieUtil;

import static spaland.config.JwtService.COOKIE_NAME;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService iUserService;
    private final UserServiceImple userServiceImple;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RedisService redisService;
    private final CookieUtil cookieUtil;


    @PostMapping("/signup")
    public ResponseEntity<Message> signup(
            @RequestBody SignupRequest signupRequest) {
        return iUserService.singup(signupRequest);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Message> getUser(@PathVariable(value = "id") Long id){
        log.info("input id ? {}",id);

        // res<user>
        // return ResponseEntity.status(Https
        return iUserService.getUser(id);
    }

//    @GetMapping("checkduplicate/{id}")
//    public boolean checkDuplicateId(@PathVariable String userid) {
//        log.info("check id ? {}",userid);
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = iUserService.login(loginRequest);
        ResponseCookie refreshTokenCookie = cookieUtil.createCookie(COOKIE_NAME, loginResponse.getRefreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponse.getToken());
        headers.add(HttpHeaders.COOKIE, refreshTokenCookie.toString());

        return ResponseEntity.ok().headers(headers).body(loginResponse.getUserNickname());

    }


    @GetMapping("/logout")
    public void logout(@RequestHeader(value = "Authorization") String accessToken) {
//                                         @RequestHeader(value = COOKIE_NAME) String refresh
//        Cookie refreshToken = new Cookie(COOKIE_NAME,refresh);
//        refreshToken.setHttpOnly(true);
//        refreshToken.setMaxAge(0);
//        httpServletResponse.addCookie(refreshToken); //쿠키 삭제
        iUserService.logout(accessToken);
        accessToken = null;


    }
}

