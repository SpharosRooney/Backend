package spaland.users.service;

import spaland.users.model.User;
import spaland.users.vo.ResponseUser;
import spaland.users.vo.SignupRequest;

public interface IUserService {

    User singup(SignupRequest signupRequest);
    ResponseUser getUser(Long id);

    boolean checkDuplicateId(String userId);
}
