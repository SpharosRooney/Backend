package spaland.api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.cart.vo.RequestCart;
import spaland.api.cart.vo.RequestCartCount;
import spaland.api.cart.vo.RequestDeleteCart;
import spaland.api.cart.service.ICartService;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService iCartService;
    @PostMapping()
    public ResponseEntity<Message> addCart(Authentication authentication, @RequestBody RequestCart requestCart){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.addCart(requestCart, userDetails.getUsername());
    }


    @GetMapping() //유저의 장바구니를 볼 수 있음(isDelete = false)
    public ResponseEntity<Message> getAllByUserCart(Authentication authentication, @RequestParam(value = "isDelete") Boolean isDelete){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.getAllByUserCart(userDetails.getUsername(), false);
    }

    @PutMapping("/modify") //장바구니 상품 수량 수정을 위한 메서드
    public ResponseEntity<Message> modifyCart(Authentication authentication,@RequestBody RequestCartCount requestCartCount){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.modifyCart(requestCartCount, userDetails.getUsername());
    }

    @PutMapping() //장바구니 상품 삭제(true,false값으로 나타냄)
    public ResponseEntity<Message> deleteProduct(Authentication authentication,@RequestBody RequestDeleteCart requestDeleteCart){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.deleteProduct(requestDeleteCart, userDetails.getUsername());
    }

}
