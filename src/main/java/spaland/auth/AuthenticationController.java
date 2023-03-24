package spaland.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import spaland.config.JwtService;
import spaland.email.service.RedisService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static spaland.config.JwtService.COOKIE_NAME;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RedisService redisService;
    private final CookieUtil cookieUtil;

    @PostMapping("/signup")
    public void signup(
            @RequestBody SignupRequest signupRequest) {
        authenticationService.signup(signupRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {

        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
//        ResponseCookie cookie = cookieUtil.createCookie(COOKIE_NAME,authenticationResponse.getRefreshToken());
        Cookie myCookie = new Cookie(COOKIE_NAME,authenticationResponse.getRefreshToken());
        myCookie.setMaxAge(JwtService.REFRESH_TOKEN_VALIDATION_SECOND);
        myCookie.setSecure(true);
        myCookie.setHttpOnly(true);
        myCookie.setPath("/");
        response.addCookie(myCookie);
//        response.setHeader(SET_COOKIE, cookie.toString() + ";");
        return ResponseEntity.ok(authenticationResponse.getToken());
    }


    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader(value = COOKIE_NAME) RefreshRequest refreshRequest,
                                          HttpServletResponse response) {
        ResponseCookie cookie = cookieUtil.createCookie(
                COOKIE_NAME,authenticationService.refresh(refreshRequest).getRefreshToken());
        response.setHeader(SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(authenticationService.refresh(refreshRequest).getToken());
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization") String request,
                                         @RequestBody LogoutRequest logoutRequest,
//                                         @CookieValue(value = COOKIE_NAME, defaultValue = "") String refresh,
                                         @RequestHeader(value = COOKIE_NAME) String refresh,
                                         HttpServletResponse httpServletResponse){


        Cookie refreshToken = new Cookie(COOKIE_NAME,refresh);
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


        return ResponseEntity.ok().body(userDetails.getUsername() + " 님 로그아웃 되었습니다");
    }


}
