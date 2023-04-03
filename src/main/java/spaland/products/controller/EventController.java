package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.products.model.Event;
import spaland.products.service.IEventService;
import spaland.products.vo.RequestEvent;
import spaland.products.vo.ResponseEvent;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final IEventService iEventService;

    @PostMapping
    public ResponseEntity<Message> addEvent(@RequestBody RequestEvent requestEvent) {
        return iEventService.addEvent(requestEvent);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Message> getEvent(@PathVariable(value = "eventId") Integer eventId) {
        return iEventService.getEvent(eventId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllEvent() {
        return iEventService.getAllEvent();
    }
}
