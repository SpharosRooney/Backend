package spaland.api.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.users.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserId(String userId);
}
