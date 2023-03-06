package spaland.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.users.service.IUserService;
import spaland.users.vo.RequestUser;
import spaland.users.vo.ResponseUser;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/add")
    public ResponseUser addUser(@RequestBody RequestUser requestUser){

        return iUserService.addUser(requestUser);

    }

    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id){
        log.info("input id ? {}",id);
        return iUserService.getUser(id);
    }


}
