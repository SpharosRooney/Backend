package spaland.users.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.users.vo.*;
import spaland.config.JwtService;
import spaland.email.service.RedisService;
import spaland.error.ApiException;
import spaland.users.model.Role;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import static spaland.error.ErrorCode.MEMBER_INVALID;
import static spaland.exception.ErrorCode.INVALID_MEMBER;

@Service
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

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUserEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new CustomException(INVALID_MEMBER);
        }

        User user = iUserRepository.findByUserEmail(loginRequest.getUserEmail())
                .orElseThrow(()-> new CustomException(INVALID_MEMBER));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(jwtToken);

        redis.createEmailByRefreshToken(refreshToken, user.getUserEmail());
        return LoginResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .userNickname(user.getUserNickname())
                .build();
    }

    public LogoutResponse logout(LogoutRequest logoutRequest){
        User user = iUserRepository.findByUserEmail(logoutRequest.getUserEmail()).get();
        return LogoutResponse.builder()
                .userNickname(user.getUserNickname()).build();
    }



    @Override
    public ResponseUser getUser(Long id) {

        User user = iUserRepository.findById(id).orElseThrow(()->new CustomException(INVALID_MEMBER));

        return new ModelMapper().map(user, ResponseUser.class);
    }

    @Override
    public boolean checkDuplicateId(String userId) {
        boolean possibleId = true;

        return true;
    }
}
