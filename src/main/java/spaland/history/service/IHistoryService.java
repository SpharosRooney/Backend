package spaland.history.service;

import spaland.history.dto.ResponseHistoryDTO;
import spaland.history.dto.ResponseHistoryDetailDTO;
import spaland.history.vo.RequestHistory;

import java.util.List;

public interface IHistoryService {
    void addHistory(RequestHistory requestHistory,String userEmail);

    ResponseHistoryDetailDTO getHistory(Integer historyId,String userEmail);

    List<ResponseHistoryDTO> findAll(String userEmail);
}
