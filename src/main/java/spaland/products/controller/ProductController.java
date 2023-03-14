package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.products.model.Product;
import spaland.products.service.IProductService;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;
    // final을 안쓰면 인식을 못한다..

    @PostMapping("/add")
    public ResponseProduct addProduct(@RequestBody RequestProduct requestProduct) {
        return iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    public ResponseProduct getProduct(@PathVariable Long productId){
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    public List<ResponseProduct> getAlLProduct() {
        return iProductService.getAllProduct();
    }
//
//    @GetMapping("/purchase/{productId}/{productNum}") // 상품 구매 전 화면.
//    public Product purchase(@PathVariable Long productId, @PathVariable int productNum) {
//        return iProductService.purchase(productId,productNum);
//    }
}
