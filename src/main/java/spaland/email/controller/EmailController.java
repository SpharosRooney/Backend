package spaland.email.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.email.service.IEmailService;
import spaland.email.vo.RequestCheckCode;
import spaland.email.vo.RequestUserEmail;

@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    private final IEmailService iEmailService;

    @GetMapping("/confirm/{email}")
    public boolean createConfirmCodeByEmail(@PathVariable String email) throws Exception {

        return iEmailService.sendConfirmCodeByEmail(email);
    }

    @PostMapping("/confirm") // 프론트와 연동
    public boolean createConfirmCodeByEmail2(@RequestBody RequestUserEmail requestUserEmail) throws Exception {
        log.info("{}", requestUserEmail);
        System.out.println(requestUserEmail);
        return iEmailService.sendConfirmCodeByEmail(requestUserEmail.getUserEmail());
    }

    @PostMapping("/checkcode")
    public boolean checkCode(@RequestBody RequestCheckCode requestCheckCode) throws Exception {

        return iEmailService.checkCode(requestCheckCode);
    }
}