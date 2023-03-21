package spaland.user.service;

import spaland.user.domain.User2;

public interface IUserService2 {

    void createUser(User2 user2);

    User2 getUser(String email);
}
