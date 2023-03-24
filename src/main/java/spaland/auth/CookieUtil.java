package spaland.auth;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import spaland.config.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static spaland.config.JwtService.COOKIE_NAME;


@Service
public class CookieUtil {
    public ResponseCookie createCookie(String cookieName, String value){
        ResponseCookie cookie = ResponseCookie.from(cookieName, value)
                .maxAge(JwtService.REFRESH_TOKEN_VALIDATION_SECOND)
                .path("/")
                .secure(true)
                .httpOnly(true)
                .sameSite("None")
                .build();
        return cookie;


    }

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
}
