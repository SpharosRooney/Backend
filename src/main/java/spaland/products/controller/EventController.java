package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public void addEvent(@RequestBody RequestEvent requestEvent) {
        iEventService.addEvent(requestEvent);
    }

    @GetMapping("/{eventId}")
    public ResponseEvent getEvent(@PathVariable(value = "eventId") Integer eventId) {
        return iEventService.getEvent(eventId);
    }

    @GetMapping("/all")
    public List<ResponseEvent> getAllEvent() {
        return iEventService.getAllEvent();
    }
}
