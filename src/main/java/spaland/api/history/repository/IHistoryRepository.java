package spaland.api.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.history.model.History;
import spaland.api.users.model.User;

import java.util.List;

public interface IHistoryRepository extends JpaRepository<History, Integer> {

    List<History> findAllByUser(User user);
}
