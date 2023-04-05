package spaland.api.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.image.vo.RequestProductImage;
import spaland.api.image.service.IProductImageService;

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
