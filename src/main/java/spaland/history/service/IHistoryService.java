package spaland.history.service;

import spaland.history.model.History;
import spaland.history.vo.RequestHistory;

import java.util.List;

public interface IHistoryService {
    void addHistory(RequestHistory requestHistory);

    History getHistory(Integer historyId);

    List<History> findAll();
}
