package spaland.api.history.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.history.vo.RequestHistory;

public interface IHistoryService {
    ResponseEntity<Message> addHistory(RequestHistory requestHistory, String userId);

    ResponseEntity<Message> getHistory(Integer historyId,String userId);

    ResponseEntity<Message> findAll(String userId);
}
