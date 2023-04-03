package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.exception.CustomException;
import spaland.products.model.Event;
import spaland.products.repository.IEventRepository;
import spaland.products.vo.RequestEvent;
import spaland.products.vo.ResponseEvent;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_EVENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImple implements IEventService {

    private final IEventRepository iEventRepository;

    @Override
    public ResponseEntity<Message> addEvent(RequestEvent requestEvent) {
        iEventRepository.save(
                Event.builder()
                        .name(requestEvent.getName())
                        .description(requestEvent.getDescription())
                        .imgUrl(requestEvent.getImgUrl())
                        .imgAlt(requestEvent.getImgAlt())
                        .startDate(requestEvent.getStartDate())
                        .endDate(requestEvent.getEndDate())
                        .discountRate(requestEvent.getDiscountRate())
                        .isDisplay(requestEvent.getIsDisplay())
                        .build()
        );

        Message message = new Message();
        message.setMessage("이벤트 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getEvent(Integer eventId) {

        Message message = new Message();
        message.setMessage("이벤트 조회 성공!");
        message.setData(new ModelMapper().map(iEventRepository.findById(eventId).orElseThrow(()-> new CustomException(INVALID_EVENT)), ResponseEvent.class));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllEvent() {
        List<Event> eventList = iEventRepository.findAll();
        List<ResponseEvent> responseEventList = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        eventList.forEach(
                event -> {
                    responseEventList.add(
                            modelMapper.map(event, ResponseEvent.class)
                    );
                }
        );
        Message message = new Message();
        message.setMessage("이벤트 전체 조회 성공!");
        message.setData(responseEventList);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
