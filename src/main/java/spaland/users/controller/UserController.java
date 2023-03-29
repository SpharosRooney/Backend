package spaland.users.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import spaland.auth.service.AuthenticationService;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.users.service.IUserService;
import spaland.users.service.UserServiceImple;
import spaland.users.vo.*;
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
    public void signup(
            @RequestBody SignupRequest signupRequest) {
        userServiceImple.singup(signupRequest);
    }


    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id){
        log.info("input id ? {}",id);
        return iUserService.getUser(id);
    }

//    @GetMapping("checkduplicate/{id}")
//    public boolean checkDuplicateId(@PathVariable String userid) {
//        log.info("check id ? {}",userid);
//        return iUserService.checkDuplicateId(userid);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userServiceImple.login(loginRequest);
        ResponseCookie refreshTokenCookie = cookieUtil.createCookie("refresh_token", loginResponse.getRefreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponse.getToken());
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return new ResponseEntity<>(loginResponse.getUserNickname(),headers, HttpStatus.OK);

    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization") String request,
                                         @RequestBody LogoutRequest logoutRequest,
                                         @RequestHeader(value = COOKIE_NAME) String refresh,
                                         HttpServletResponse httpServletResponse){


        Cookie refreshToken = new Cookie(COOKIE_NAME,refresh);
        refreshToken.setHttpOnly(true);
        refreshToken.setMaxAge(0);
        httpServletResponse.addCookie(refreshToken); //쿠키 삭제

        UserDetails userDetails = userDetailsService.loadUserByUsername(logoutRequest.getUserEmail());

        if (request == null || !request.startsWith("Bearer ")) {
            return null;
        }

        String accessToken = request.substring(7);
        if(Boolean.FALSE.equals(jwtService.isTokenValid(accessToken,userDetails))){
            throw new RuntimeException("잘못된 요청 입니다");
        }

        Long expiration = jwtService.getExpiration(accessToken);
        if(expiration > 0L) {
            redisService.createBlacklistToken(accessToken, expiration);
        } //accessToken은 블랙리스트에 넣음


        String userEmail = redisService.getEmailByRefreshToken(refresh);
        if(userEmail != null){
            redisService.removeEmailByRefreshToken(refresh); //레디스 삭제

        }
        LogoutResponse logoutResponse = userServiceImple.logout(logoutRequest);

        return ResponseEntity.ok().body( logoutResponse.getUserNickname()+ " 님 로그아웃 되었습니다");
    }



}
