package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.products.model.ProductImage;
import spaland.products.service.IProductImageService;
import spaland.products.vo.RequestProductImage;
import spaland.products.vo.ResponseProductImage;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/image")
@Slf4j
@RequiredArgsConstructor
public class ProductImageController {

    private final IProductImageService iProductImageService;

    @PostMapping
    public ResponseEntity<Message> addProductImage(@RequestBody RequestProductImage requestProductImage) {
        return iProductImageService.addProductImage(requestProductImage);
    }

    @GetMapping("/{productImageId}")
    public ResponseEntity<Message> getProductImage(@PathVariable(value = "productImageId") Long productImageId) {
        return iProductImageService.getProductImage(productImageId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllProductImage() {
        return iProductImageService.getAllProductImage();
    }
}
