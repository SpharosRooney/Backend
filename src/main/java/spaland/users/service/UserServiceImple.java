package spaland.users.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;
import spaland.users.vo.RequestUser;
import spaland.users.vo.ResponseUser;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements IUserService{

    private final IUserRepository iUserRepository;

    @Override
    public ResponseUser addUser(RequestUser requestUser) {

        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .userNickname(requestUser.getUserNickname()+"#"+uuid.toString())
                .userEmail(requestUser.getUserEmail())
                .userName(requestUser.getUserName())
                .password(requestUser.getPassword())
                .phone(requestUser.getPhone())
                .build();

                iUserRepository.save(user);

        return new ModelMapper().map(user, ResponseUser.class);
    }

    @Override
    public ResponseUser getUser(Long id) {
        User user = iUserRepository.findById(id).get();

        return new ModelMapper().map(user, ResponseUser.class);
    }

    @Override
    public boolean checkDuplicateId(String userId) {
        boolean possibleId = true;

        return true;
    }
}
