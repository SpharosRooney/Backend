package spaland.api.email.service;

import spaland.api.email.vo.RequestCheckCode;

public interface IEmailService {

    boolean sendConfirmCodeByEmail(String email) throws Exception;
    boolean checkCode(RequestCheckCode requestCheckCode);
}
