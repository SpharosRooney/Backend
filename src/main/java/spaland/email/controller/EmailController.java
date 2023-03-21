package spaland.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spaland.email.service.IEmailService;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final IEmailService iEmailService;

    @GetMapping("/confirm/{email}")
    public String createConfirmCodeByEmail(@PathVariable String email) throws Exception {
        return iEmailService.sendConfirmCodeByEmail(email);
    }
}
