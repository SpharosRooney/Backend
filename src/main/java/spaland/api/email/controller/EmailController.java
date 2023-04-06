package spaland.api.email.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import spaland.api.email.service.IEmailService;
import spaland.api.email.vo.RequestCheckCode;
import spaland.api.email.vo.RequestUserEmail;

@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    private final IEmailService iEmailService;


    @PostMapping("/confirm")
    public boolean createConfirmCodeByEmail(@RequestBody RequestUserEmail requestUserEmail) throws Exception {
        log.info("{}", requestUserEmail);
        System.out.println(requestUserEmail);
        return iEmailService.sendConfirmCodeByEmail(requestUserEmail.getUserEmail());
    }

    @PostMapping("/checkcode")
    public boolean checkCode(@RequestBody RequestCheckCode requestCheckCode) throws Exception {

        return iEmailService.checkCode(requestCheckCode);
    }
}