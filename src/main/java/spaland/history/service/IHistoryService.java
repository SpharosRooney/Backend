package spaland.history.service;

import spaland.history.dto.ResponseHistoryDTO;
import spaland.history.dto.ResponseHistoryDetailDTO;
import spaland.history.vo.RequestHistory;

import java.util.List;

public interface IHistoryService {
    void addHistory(RequestHistory requestHistory);

    ResponseHistoryDetailDTO getHistory(Integer historyId);

    List<ResponseHistoryDTO> findAll(Long userId);
}
