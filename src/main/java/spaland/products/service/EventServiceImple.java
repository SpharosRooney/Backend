package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.Event;
import spaland.products.repository.IEventRepository;
import spaland.products.vo.RequestEvent;
import spaland.products.vo.ResponseEvent;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImple implements IEventService {

    private final IEventRepository iEventRepository;

    @Override
    public void addEvent(RequestEvent requestEvent) {
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
    }

    @Override
    public ResponseEvent getEvent(Integer eventId) {

        return new ModelMapper().map(iEventRepository.findById(eventId).get(), ResponseEvent.class);
    }

    @Override
    public List<ResponseEvent> getAllEvent() {
        List<Event> eventList = iEventRepository.findAll();
        List<ResponseEvent> responseEventList = new ArrayList<>();

        eventList.forEach(
                event -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseEventList.add(
                            modelMapper.map(event, ResponseEvent.class)
                    );
                }
        );
        return responseEventList;
    }
}
