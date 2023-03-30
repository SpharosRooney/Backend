package spaland.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.history.model.History;
import spaland.users.model.User;

import java.util.List;
import java.util.Optional;

public interface IHistoryRepository extends JpaRepository<History, Integer> {

    List<History> findAllByUser(User user);
}
