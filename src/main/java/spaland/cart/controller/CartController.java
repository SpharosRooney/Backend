package spaland.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.cart.service.ICartService;
import spaland.cart.vo.*;
import spaland.products.service.IProductService;

import java.util.List;

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

    @GetMapping("/{userId}") //유저의 장바구니를 볼 수 있음(isDelete = false)
    public ResponseEntity<List<ResponseGetUserCart>> getAllByUserCart(@RequestParam("userId") Long userId, @RequestParam("isDelete") Boolean isDelete){
        return ResponseEntity.ok(
                iCartService.getAllByUserCart(userId,isDelete)
        );
    }

//    @GetMapping("/cartHistory")//유저가 가진 장바구니 + 삭제한 장바구니 상품
//    public ResponseEntity<List<ResponseGetUserCart>> getAllByUser(@PathVariable Long userId){
//        return ResponseEntity.ok(
//                iCartService.getAllByUser(userId)
//        );
//    }

    @PutMapping("/modify") //장바구니 상품 수량 수정을 위한 메서드
    public void modifyCart(@RequestBody RequestCartCount requestCartCount){
        iCartService.modifyCart(requestCartCount);
    }

    @PutMapping() //장바구니 상품 삭제(true,false값으로 나타냄)
    public void deleteProduct(@RequestBody RequestDeleteCart requestDeleteCart){
        iCartService.deleteProduct(requestDeleteCart);
    }


}
