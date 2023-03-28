package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.Event;

public interface IEventRepository extends JpaRepository<Event, Integer>{
}
