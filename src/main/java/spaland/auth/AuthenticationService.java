package spaland.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.user.domain.Role;
import spaland.user.domain.User2;
import spaland.user.repository.IUserRepository2;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final IUserRepository2 userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final RedisService redis;
        private final UserDetailsService userDetailsService;


        public AuthenticationResponse signup(SignupRequest signupRequest) {
            var user = User2.builder()
                    .userName(signupRequest.getName())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .email(signupRequest.getEmail())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.refreshToken(jwtToken);
            redis.createEmailByRefreshToken(refreshToken, user.getEmail());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(); //
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.refreshToken(jwtToken);
            redis.createEmailByRefreshToken(refreshToken, user.getEmail());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
            // create code check if refresh token is valid
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshRequest.getRefreshToken()));

            var redisUserEmail = redis.getEmailByRefreshToken(refreshRequest.getRefreshToken());
            if( !userDetails.getUsername().equals(redisUserEmail) && !jwtService.isTokenValid(refreshRequest.getRefreshToken(), userDetails) ) {
                throw new RuntimeException("Refresh token is not valid");
            }

            var jwtAToken = jwtService.generateToken(userDetails);
            var jwtRToken = jwtService.refreshToken(jwtAToken);

            return AuthenticationResponse.builder()
                    .token(jwtAToken)
                    .refreshToken(jwtRToken)
                    .build();
        }
}
