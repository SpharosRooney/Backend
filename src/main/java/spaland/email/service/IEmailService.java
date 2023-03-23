package spaland.email.service;

import spaland.email.vo.RequestCheckCode;

public interface IEmailService {

    boolean sendConfirmCodeByEmail(String email) throws Exception;
    String sendReissuePassword(String email) throws Exception;

    boolean checkCode(RequestCheckCode requestCheckCode);
}
