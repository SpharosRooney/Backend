package spaland.history.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.history.model.History;
import spaland.history.service.IHistoryService;
import spaland.history.vo.RequestHistory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
@Slf4j
public class HistoryController {

    private final IHistoryService iHistoryService;

    @PostMapping("/add")
    public void addHistory(@RequestBody RequestHistory requestHistory) {
        iHistoryService.addHistory(requestHistory);
    }

    @GetMapping("/get/{historyId}")
    public History getHistory(@PathVariable Integer historyId) {
        return iHistoryService.getHistory(historyId);
    }

    @GetMapping("/get/all")
    public List<History> getAll() { return iHistoryService.findAll();}


}
