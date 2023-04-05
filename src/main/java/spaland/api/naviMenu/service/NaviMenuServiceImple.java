package spaland.api.naviMenu.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.image.model.ProductImage;
import spaland.api.image.vo.ResponseProductImage;
import spaland.api.naviMenu.model.NaviMenu;
import spaland.api.naviMenu.repository.INaviMenuRepository;
import spaland.api.naviMenu.vo.RequestNaviMenu;
import spaland.api.naviMenu.vo.ResponseNaviMenu;
import spaland.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_IMAGE;

@RequiredArgsConstructor
@Service
public class NaviMenuServiceImple implements INaviMenuService{

    private final INaviMenuRepository iNaviMenuRepository;

    @Override
    public ResponseEntity<Message> addNaviMenu(RequestNaviMenu requestNaviMenu) {
        iNaviMenuRepository.save(
                NaviMenu.builder()
                        .name(requestNaviMenu.getName())
                        .link(requestNaviMenu.getLink())
                        .build()
        );

        Message message = new Message();
        message.setMessage("메인페이지 타이틀 추가 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllNaviMenu() {
        List<NaviMenu> naviMenuList = iNaviMenuRepository.findAll();
        List<ResponseNaviMenu> responseNaviMenus = new ArrayList<>();

        naviMenuList.forEach(
                naviMenu -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseNaviMenus.add(
                            modelMapper.map(naviMenu, ResponseNaviMenu.class));
                });
        Message message = new Message();
        message.setMessage("메인페이지 타이틀 전체 조회 성공!");
        message.setData(responseNaviMenus);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
