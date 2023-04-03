package spaland.history.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.history.dto.ResponseHistoryDTO;
import spaland.history.dto.ResponseHistoryDetailDTO;
import spaland.history.vo.RequestHistory;

import java.util.List;

public interface IHistoryService {
    ResponseEntity<Message> addHistory(RequestHistory requestHistory, String userId);

    ResponseHistoryDetailDTO getHistory(Integer historyId,String userId);

    List<ResponseHistoryDTO> findAll(String userId);
}
