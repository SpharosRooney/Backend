package spaland.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.users.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
}
