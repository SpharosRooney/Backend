package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.products.model.Event;
import spaland.products.repository.IEventRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImple implements IEventService {

    private final IEventRepository iEventRepository;

    @Override
    public void addEvent(Event event) {
        iEventRepository.save(event);
    }

    @Override
    public Event getEvent(Integer eventId) {
        return iEventRepository.findById(eventId).get();
    }

    @Override
    public List<Event> getAllEvent() {
        return iEventRepository.findAll();
    }
}
