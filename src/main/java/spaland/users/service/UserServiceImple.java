package spaland.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

import java.util.UUID;

import static spaland.error.ErrorCode.MEMBER_INVALID;
import static spaland.exception.ErrorCode.INVALID_MEMBER;
import static spaland.exception.ErrorCode.INVALID_MEMBER_INFO;

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
                .userId(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();
        return iUserRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {

        // @todo 이메일로 아이디 찾을 때 , 오류 처리.
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                iUserRepository.findByUserEmail(loginRequest.getUserEmail()).get().getUserId(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {

            throw new CustomException(INVALID_MEMBER_INFO);
        }

        User user = iUserRepository.findByUserEmail(loginRequest.getUserEmail())
                .orElseThrow(()-> new CustomException(INVALID_MEMBER));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(jwtToken);

//        redis.createEmailByRefreshToken(refreshToken, user.getUserId());
        return LoginResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .userNickname(user.getUserNickname())
                .build();
    }

    public void logout(String access){

        if (access == null || !access.startsWith("Bearer ")) {
            //todo 잘못된 토큰
        }

        String accessToken = access.substring(7);
        User user = iUserRepository.findByUserId(jwtService.extractUsername(accessToken)).get();

        if(Boolean.FALSE.equals(jwtService.isTokenValid(accessToken,user))){
            throw new RuntimeException("잘못된 요청 입니다"); //todo 토큰 유효x
        }

        Long expiration = jwtService.getExpiration(accessToken);
        if(expiration > 0L) {
            redis.createBlacklistToken(accessToken, expiration);
        } //accessToken은 블랙리스트에 넣음
//
//        String refresh = jwtService.extractUsername(accessToken);
//        String userEmail = redis.getEmailByRefreshToken(refresh);
//        if(userEmail != null){
//            redis.removeEmailByRefreshToken(refresh); //레디스 삭제

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
