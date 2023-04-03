package spaland.api.history.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.history.vo.RequestHistory;
import spaland.api.history.service.IHistoryService;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
@Slf4j
public class HistoryController {

    private final IHistoryService iHistoryService;

    @PostMapping
    public ResponseEntity<Message> addHistory(Authentication authentication, @RequestBody RequestHistory requestHistory) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iHistoryService.addHistory(requestHistory, userDetails.getUsername());
    }

    //히스토리 아이디로 조회하기 위해서, 히스토리 전체를 받았을 때,
    @GetMapping("/{historyId}")
    public ResponseEntity<Message> getHistory(Authentication authentication, @PathVariable(value = "historyId") Integer historyId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iHistoryService.getHistory(historyId, userDetails.getUsername());
    }

    @GetMapping()
    public ResponseEntity<Message> getAllbyUserId(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iHistoryService.findAll(userDetails.getUsername());
    }

}
