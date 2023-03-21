package spaland.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.user.domain.User2;

import java.util.Optional;

public interface IUserRepository2 extends JpaRepository<User2, Long> {

    Optional<User2> findByEmail(String email);
}
