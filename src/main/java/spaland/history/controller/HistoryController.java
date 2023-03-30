package spaland.history.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void addHistory(Authentication authentication, @RequestBody RequestHistory requestHistory) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        iHistoryService.addHistory(requestHistory, userDetails.getUsername());
    }

    //히스토리 아이디로 조회하기 위해서, 히스토리 전체를 받았을 때,
    @GetMapping("/{historyId}")
    public ResponseHistoryDetailDTO getHistory(Authentication authentication, @PathVariable(value = "historyId") Integer historyId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iHistoryService.getHistory(historyId, userDetails.getUsername());
    }

    @GetMapping()
    public List<ResponseHistoryDTO> getAllbyUserId(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iHistoryService.findAll(userDetails.getUsername());
    }

}
