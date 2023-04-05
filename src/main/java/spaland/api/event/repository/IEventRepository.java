package spaland.api.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.event.model.Event;

public interface IEventRepository extends JpaRepository<Event, Integer>{
}
