package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.Event;
import spaland.products.vo.RequestEvent;
import spaland.products.vo.ResponseEvent;

import java.util.List;

public interface IEventService {
    ResponseEntity<Message> addEvent(RequestEvent requestEvent);
    ResponseEntity<Message> getEvent(Integer eventId);
    ResponseEntity<Message> getAllEvent();
}
