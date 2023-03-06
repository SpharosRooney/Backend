package spaland.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.users.model.User;

public interface IUserRespository extends JpaRepository<User, Long> {

}
