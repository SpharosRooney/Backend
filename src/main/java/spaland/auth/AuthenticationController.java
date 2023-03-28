package spaland.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import spaland.auth.vo.LogoutRequest;
import spaland.auth.vo.RefreshRequest;
import spaland.auth.vo.SignupRequest;
import spaland.config.JwtService;
import spaland.email.service.RedisService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
//        Cookie myCookie = cookieUtil.cookie(COOKIE_NAME,authenticationResponse.getRefreshToken());
//        response.addCookie(myCookie);


        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authenticationResponse.getToken())
                .header(HttpHeaders.SET_COOKIE, "refresh-token=" + authenticationResponse.getRefreshToken()
                        + "; domain= localhost; path=/; SameSite=None; Secure; httpOnly;")
                .build();
        
    }


    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader(value = COOKIE_NAME) RefreshRequest refreshRequest,
                                          HttpServletResponse response) {
        Cookie myCookie = cookieUtil.cookie(
                COOKIE_NAME,authenticationService.refresh(refreshRequest).getRefreshToken());
        response.addCookie(myCookie);
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
