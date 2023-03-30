package spaland.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.users.vo.*;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.error.ApiException;
import spaland.users.model.Role;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import static spaland.error.ErrorCode.MEMBER_INVALID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImple implements IUserService{

    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redis;

    @Override
    public User singup(SignupRequest signupRequest) {
        var user = User.builder()
                .userName(signupRequest.getUserName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .userEmail(signupRequest.getUserEmail())
                .userNickname(signupRequest.getUserNickname())
                .phone(signupRequest.getPhone())
                .role(Role.USER)
                .build();
        return iUserRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                iUserRepository.findByUserEmail(loginRequest.getUserEmail()).get().getUserId(), loginRequest.getPassword()
        ));

        User user = iUserRepository.findByUserEmail(loginRequest.getUserEmail())
                .orElseThrow(()-> new ApiException(MEMBER_INVALID));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(jwtToken);

        redis.createEmailByRefreshToken(refreshToken, user.getUserId());
        return LoginResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .userNickname(user.getUserNickname())
                .build();
    }

    public LogoutResponse logout(String access, String refresh){

        if (access == null || !access.startsWith("Bearer ")) {
            return null;
        }

        String accessToken = access.substring(7);
        User user = iUserRepository.findByUserId(jwtService.extractUsername(accessToken)).get();

        if(Boolean.FALSE.equals(jwtService.isTokenValid(accessToken,user))){
            throw new RuntimeException("잘못된 요청 입니다");
        }

        Long expiration = jwtService.getExpiration(accessToken);
        if(expiration > 0L) {
            redis.createBlacklistToken(accessToken, expiration);
        } //accessToken은 블랙리스트에 넣음


        String userEmail = redis.getEmailByRefreshToken(refresh);
        if(userEmail != null){
            redis.removeEmailByRefreshToken(refresh); //레디스 삭제

        }


        return null;
    }



    @Override
    public ResponseUser getUser(Long id) {
        User user = iUserRepository.findById(id).get();

        return new ModelMapper().map(user, ResponseUser.class);
    }

    @Override
    public boolean checkDuplicateId(String userId) {
        boolean possibleId = true;

        return true;
    }
}
