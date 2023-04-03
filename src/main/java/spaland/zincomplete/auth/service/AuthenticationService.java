package spaland.zincomplete.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import spaland.api.users.vo.LoginResponse;
import spaland.config.JwtService;
import spaland.api.email.service.RedisService;
import spaland.zincomplete.auth.vo.RefreshRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final RedisService redis;
    private final UserDetailsService userDetailsService;


    public LoginResponse refresh(RefreshRequest refreshRequest) {
        // create code check if refresh token is valid
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshRequest.getRefreshToken()));

        var redisUserEmail = redis.getEmailByRefreshToken(refreshRequest.getRefreshToken());
        if (!userDetails.getUsername().equals(redisUserEmail) &&
                !jwtService.isTokenValid(refreshRequest.getRefreshToken(), userDetails)) {
            throw new RuntimeException("Refresh token is not valid");
        }
        var jwtAToken = jwtService.generateToken(userDetails);
        var jwtRToken = refreshRequest.getRefreshToken();
        return LoginResponse.builder()
                .token(jwtAToken)
//                .refreshToken(jwtRToken)
                .build();
    }
}