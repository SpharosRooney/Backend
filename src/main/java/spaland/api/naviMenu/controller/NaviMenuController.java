package spaland.api.naviMenu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.naviMenu.service.INaviMenuService;
import spaland.api.naviMenu.vo.RequestNaviMenu;

@RestController
@RequestMapping("/api/v1/naviMenu")
@RequiredArgsConstructor
public class NaviMenuController {

    private final INaviMenuService iNaviMenuService;

    @PostMapping
    public ResponseEntity<Message> addNaviMenu(@RequestBody RequestNaviMenu requestNaviMenu) {
        return iNaviMenuService.addNaviMenu(requestNaviMenu);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllNaviMenu() {
        return iNaviMenuService.getAllNaviMenu();
    }
}
