package spaland.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.users.model.Role;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final IUserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final RedisService redis;
        private final UserDetailsService userDetailsService;


        public User signup(SignupRequest signupRequest) {
            UUID uuid = UUID.randomUUID();
            var user = User.builder()
                    .userName(signupRequest.getUserName())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .userEmail(signupRequest.getUserEmail())
                    .userNickname(signupRequest.getUserNickname()+"#"+uuid.toString())
                    .phone(signupRequest.getPhone())
                    .role(Role.USER)
                    .build();
            return userRepository.save(user);
        }

        public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserEmail(), authenticationRequest.getPassword()));

            var user = userRepository.findByUserEmail(authenticationRequest.getUserEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.refreshToken(jwtToken);

            redis.createEmailByRefreshToken(refreshToken, user.getUserEmail());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
            // create code check if refresh token is valid
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshRequest.getRefreshToken()));

            var redisUserEmail = redis.getEmailByRefreshToken(refreshRequest.getRefreshToken());
            if (!userDetails.getUsername().equals(redisUserEmail) &&
                    !jwtService.isTokenValid(refreshRequest.getRefreshToken(), userDetails)) {
                throw new RuntimeException("Refresh token is not valid");
            }
            var jwtAToken = jwtService.generateToken(userDetails);
            var jwtRToken = refreshRequest.getRefreshToken();
            return AuthenticationResponse.builder()
                    .token(jwtAToken)
                    .refreshToken(jwtRToken)
                    .build();

        }
}
