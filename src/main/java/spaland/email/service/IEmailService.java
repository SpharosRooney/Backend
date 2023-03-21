package spaland.email.service;

public interface IEmailService {

    String sendConfirmCodeByEmail(String email) throws Exception;
    String sendReissuePassword(String email) throws Exception;

}
