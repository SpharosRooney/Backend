package spaland.products.service;

import spaland.products.model.Event;
import spaland.products.vo.RequestEvent;
import spaland.products.vo.ResponseEvent;

import java.util.List;

public interface IEventService {
    void addEvent(RequestEvent requestEvent);
    ResponseEvent getEvent(Integer eventId);
    List<ResponseEvent> getAllEvent();
}
