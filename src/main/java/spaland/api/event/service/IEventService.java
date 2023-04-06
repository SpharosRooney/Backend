package spaland.api.event.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.event.vo.RequestEvent;

public interface IEventService {
    ResponseEntity<Message> addEvent(RequestEvent requestEvent);
    ResponseEntity<Message> getEvent(Integer eventId);
    ResponseEntity<Message> getAllEvent();
}
