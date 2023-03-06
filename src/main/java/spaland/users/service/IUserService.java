package spaland.users.service;

import spaland.users.vo.RequestUser;
import spaland.users.vo.ResponseUser;

public interface IUserService {

    ResponseUser addUser(RequestUser requestUser);
    ResponseUser getUser(Long id);

}
