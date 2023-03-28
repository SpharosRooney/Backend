package spaland.products.service;

import spaland.products.model.Event;

import java.util.List;

public interface IEventService {
    void addEvent(Event event);
    Event getEvent(Integer eventId);
    List<Event> getAllEvent();
}
