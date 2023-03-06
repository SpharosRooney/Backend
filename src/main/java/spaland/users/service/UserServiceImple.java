package spaland.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spaland.users.model.User;
import spaland.users.repository.IUserRespository;
import spaland.users.vo.RequestUser;
import spaland.users.vo.ResponseUser;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements IUserService{

    private final IUserRespository iUserRespository;

    @Override
    public ResponseUser addUser(RequestUser requestUser) {

        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .userId(uuid.toString())
                .name(requestUser.getName())
                .email(requestUser.getEmail())
                .password(requestUser.getPassword())
                .build();

        User resUser = iUserRespository.save(user);

        ResponseUser responseUser = ResponseUser.builder()
                .id(resUser.getId())
                .name(resUser.getName())
                .email(resUser.getEmail())
                .address(resUser.getAddress())
                .build();

        return responseUser;
    }

    @Override
    public ResponseUser getUser(Long id) {
        User user = iUserRespository.findById(id).get();
        ResponseUser resUser = ResponseUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
        return resUser;
    }
}
