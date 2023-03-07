package spaland.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.cart.model.Cart;
import spaland.cart.service.ICartService;
import spaland.cart.vo.RequestCart;
import spaland.cart.vo.RequestCartCount;
import spaland.cart.vo.RequestDeleteCart;
import spaland.products.model.Product;
import spaland.products.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService iCartService;
    private final IProductService iProductService;

    @PostMapping("/add")
    public void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping("/get/userId/{userId}")
    public List<Cart> getAllByUserId(@PathVariable Long userId){
        return iCartService.getByUserId(userId);
    }

    @GetMapping("/get/productId/{productId}")
    public Product getByProductId(@PathVariable Long productId){
        return iProductService.getProduct(productId);
    }

    @PutMapping("/modify") //장바구니 상품 수량 수정을 위한 메서드
    public void modifyCart(@RequestBody RequestCartCount requestCartCount){
        iCartService.modifyCart(requestCartCount);
    }

    @PutMapping("/delete/product")
    public void deleteProduct(@RequestBody RequestDeleteCart requestDeleteCart){
        iCartService.deleteProduct(requestDeleteCart);
    }




}
