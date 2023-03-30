package spaland.history.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.history.dto.ResponseHistoryDTO;
import spaland.history.dto.ResponseHistoryDetailDTO;
import spaland.history.service.IHistoryService;
import spaland.history.vo.RequestHistory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
@Slf4j
public class HistoryController {

    private final IHistoryService iHistoryService;

    @PostMapping
    public void addHistory(@RequestBody RequestHistory requestHistory) {
        iHistoryService.addHistory(requestHistory);
    }

    // @todo 히스토리 아이디로 조회하기 위해서, 히스토리 전체를 받았을 때,
    @GetMapping("/get/{historyId}")
    public ResponseHistoryDetailDTO getHistory(@PathVariable Integer historyId) {
        return iHistoryService.getHistory(historyId);
    }

    @GetMapping("/all/{userId}")
    public List<ResponseHistoryDTO> getAllbyUserId(@PathVariable Long userId) {
        return iHistoryService.findAll(userId);}
}
