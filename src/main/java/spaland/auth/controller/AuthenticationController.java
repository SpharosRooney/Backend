package spaland.auth.controller;


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
import spaland.users.vo.*;
import spaland.utility.CookieUtil;
import spaland.auth.service.AuthenticationService;
import spaland.auth.vo.*;
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
    private final CookieUtil cookieUtil;

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader(value = COOKIE_NAME) RefreshRequest refreshRequest,
                                          HttpServletResponse response) {
        Cookie myCookie = cookieUtil.cookie(
                COOKIE_NAME,authenticationService.refresh(refreshRequest).getRefreshToken());

        response.addCookie(myCookie);
        return ResponseEntity.ok(authenticationService.refresh(refreshRequest).getToken());
    }
}
