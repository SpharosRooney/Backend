package spaland.api.naviMenu.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.image.vo.RequestProductImage;
import spaland.api.naviMenu.vo.RequestNaviMenu;

public interface INaviMenuService {
    ResponseEntity<Message> addNaviMenu(RequestNaviMenu requestNaviMenu);
    ResponseEntity<Message> getAllNaviMenu();
}
