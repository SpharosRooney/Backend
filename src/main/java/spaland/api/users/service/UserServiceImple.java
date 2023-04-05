package spaland.api.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.users.vo.LoginRequest;
import spaland.api.users.vo.LoginResponse;
import spaland.api.users.vo.ResponseUser;
import spaland.api.users.vo.SignupRequest;
import spaland.exception.CustomException;
import spaland.config.JwtService;
import spaland.api.email.service.RedisService;
import spaland.api.users.model.Role;
import spaland.api.users.model.User;
import spaland.api.users.repository.IUserRepository;

import java.util.UUID;

import static spaland.exception.ErrorCode.*;

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
    public ResponseEntity<Message> singup(SignupRequest signupRequest) {

        if(iUserRepository.findByUserEmail(signupRequest.getUserEmail()).isPresent()) {
            throw new CustomException(DUPLICATE_EMAIL_2);
        }
        
         var user = User.builder()
                .userName(signupRequest.getUserName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .userEmail(signupRequest.getUserEmail())
                .userNickname(signupRequest.getUserNickname())
                .phone(signupRequest.getPhone())
                .userId(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();
        iUserRepository.save(user);

        Message message = new Message();
        message.setMessage("회원가입 성공");
        message.setData(new ModelMapper().map(user, ResponseUser.class));

        return new ResponseEntity<>(message, HttpStatus.OK);
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
        return LoginResponse.builder()
                .token(jwtToken)
                .userNickname(user.getUserNickname())
                .build();
    }

    public void logout(String accessToken){

        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            //todo 잘못된 토큰
        }

        String access = accessToken.substring(7);


        User user = iUserRepository.findByUserId(jwtService.extractUsername(access)).get();



        if(Boolean.FALSE.equals(jwtService.isTokenValid(access,user))){
            throw new RuntimeException("잘못된 요청 입니다"); //todo 토큰 유효x
        }

        Long expiration = jwtService.getExpiration(access);
        if(expiration > 0L) {
            redis.createBlacklistToken(access, expiration);
        } //accessToken은 블랙리스트에 넣음
//
//        String refresh = jwtService.extractUsername(accessToken);
//        String userEmail = redis.getEmailByRefreshToken(refresh);
//        if(userEmail != null){
//            redis.removeEmailByRefreshToken(refresh); //레디스 삭제

    }




    @Override
    public ResponseEntity<Message> getUser(Long id) {

        User user = iUserRepository.findById(id).orElseThrow(()->new CustomException(INVALID_MEMBER));

        Message message = new Message();
        message.setMessage("로그인 성공");
        message.setData(new ModelMapper().map(user, ResponseUser.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public boolean checkDuplicateId(String userId) {
        boolean possibleId = true;

        return true;
    }
}
