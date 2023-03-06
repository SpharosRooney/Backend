package spaland.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.history.model.History;

public interface IHistoryRepository extends JpaRepository<History, Integer> {
}
