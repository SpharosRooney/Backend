package spaland.history.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.history.model.History;
import spaland.history.repository.IHistoryRepository;
import spaland.history.vo.RequestHistory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements IHistoryService{

    private final IHistoryRepository iHistoryRepository;

    @Override
    public void addHistory(RequestHistory requestHistory) {
        ModelMapper modelMapper = new ModelMapper();
        History history = modelMapper.map(requestHistory, History.class);
        iHistoryRepository.save(history);
    }

    @Override
    public History getHistory(Integer historyId) {
        return iHistoryRepository.findById(historyId).get();
    }

    @Override
    public List<History> findAll() {
        return iHistoryRepository.findAll();
    }
}
