package spaland.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.user.domain.User2;
import spaland.user.repository.IUserRepository2;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl2 implements IUserService2 {

    private final IUserRepository2 iUserRepository2;

    @Override
    public void createUser(User2 user2) {
        iUserRepository2.save(user2);
    }

    @Override
    public User2 getUser(String email) {
        return iUserRepository2.findByEmail(email).get();
    }
}
