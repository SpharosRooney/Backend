package spaland.zincomplete.auth.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.utility.CookieUtil;
import spaland.zincomplete.auth.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import spaland.zincomplete.auth.vo.RefreshRequest;

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
