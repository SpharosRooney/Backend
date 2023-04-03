package spaland.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.auth.vo.*;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.error.ApiException;
import spaland.users.model.Role;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;
import spaland.users.vo.*;

import static spaland.error.ErrorCode.MEMBER_INVALID;

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