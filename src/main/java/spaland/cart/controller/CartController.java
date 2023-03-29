package spaland.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.cart.service.ICartService;
import spaland.cart.vo.*;
import spaland.products.service.IProductService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService iCartService;
    private final IProductService iProductService;


    @PostMapping()
    public void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping() //유저의 장바구니를 볼 수 있음(isDelete = false)
    public ResponseEntity<List<ResponseGetUserCart>> getAllByUserCart(Authentication authentication, @RequestParam Boolean isDelete){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(iCartService.getAllByUserCart(userDetails.getUsername(), false));
    }


    @GetMapping("/{userId}")//유저가 가진 장바구니 + 삭제한 장바구니 상품
    public ResponseEntity<List<ResponseGetUserCart>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(
                iCartService.getAllByUser(userId)
        );
    }

    @PutMapping("/modify") //장바구니 상품 수량 수정을 위한 메서드
    public void modifyCart(@RequestBody RequestCartCount requestCartCount){
        iCartService.modifyCart(requestCartCount);
    }

    @PutMapping("/delete/product") //장바구니 상품 삭제를 true,false값으로 나타냄
    public void deleteProduct(@RequestBody RequestDeleteCart requestDeleteCart){
        iCartService.deleteProduct(requestDeleteCart);
    }


}
