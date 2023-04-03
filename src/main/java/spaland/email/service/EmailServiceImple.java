package spaland.email.service;


import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import spaland.email.vo.RequestCheckCode;
import spaland.exception.CustomException;
import spaland.exception.ErrorCode;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import java.util.Optional;
import java.util.Random;

import static spaland.exception.ErrorCode.DIFFERENT_CONFIRM_KEY;
import static spaland.exception.ErrorCode.DUPLICATE_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImple implements  IEmailService{

    @Autowired
    JavaMailSender javaMailSender;
    private final Environment env;
    private final RedisService redisService;
    private final IUserRepository iUserRepository;

    private MimeMessage createMessage(String email, String confirmKey) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("인증번호를 입력해주세요.");

        String msgg = "";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 STARBUCKS 입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 인증 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:purple;'>비밀번호 찾기 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= confirmKey+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(env.getProperty("AdminMail.id"), "STARBUCKS"));//보내는사람
        return message;
    }

    private static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for( int i = 0; i < 8; i++ ){
            int index = rnd.nextInt(3);

            switch ( index ) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public boolean sendConfirmCodeByEmail(String email) throws Exception {
        String confirmKey = createKey();
        System.out.println(email);
        MimeMessage message = createMessage(email, confirmKey);
        redisService.createConfirmCodeByEmail(email, confirmKey);
        // @todo 받아온 이메일이 DB에 존재할 경우 에러 처리 => 중복 확인
        // redis에 키, 값 저장하는 메서드 중복됨
//        Optional<User> user = iUserRepository.findByUserEmail(email);
        System.out.println("tq" + iUserRepository.findByUserEmail(email).isPresent());
        if(iUserRepository.findByUserEmail(email).isPresent()) {

            System.out.println("gd");
            throw new CustomException(DUPLICATE_EMAIL);

        }

//        if(user.isPresent()) {
//            return false;
//        }
        try {

            log.info("Success Send Email : {} {}", email, confirmKey);
//            if(redisService.getConfirmCodeByEmail(email) != null) {
//                redisService.removeConfirmCodeByEmail(email);
//            }
            redisService.createConfirmCodeByEmail(email, confirmKey);
            // @todo mailsender 오류날 때 에러 처리
            javaMailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalAccessException();
        }
    }

    @Override
    public String sendReissuePassword(String email) throws Exception {
        return null;
    }

    @Override
    public boolean checkCode(RequestCheckCode requestCheckCode) {
//        boolean matchCode = false;

        if(redisService.getConfirmCodeByEmail(requestCheckCode.getUserEmail()).equals(requestCheckCode.getConfirmKey())) {
            redisService.removeConfirmCodeByEmail(requestCheckCode.getUserEmail());
//            matchCode = true;
        } else {
            throw new CustomException(DIFFERENT_CONFIRM_KEY);
        }

        return true;
    }
}
