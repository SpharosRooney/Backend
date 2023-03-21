package spaland.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.user.domain.User2;
import spaland.user.service.IUserService2;

@RestController
@RequestMapping("/api/v1/user2")
@RequiredArgsConstructor
public class UserController2 {

    private final IUserService2 iUserService2;

    @PostMapping
    public void createUser(@RequestBody User2 user2) {
        iUserService2.createUser(user2);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User2> getUser(@PathVariable String email) {
        return ResponseEntity.ok().body(iUserService2.getUser(email));
    }
}
