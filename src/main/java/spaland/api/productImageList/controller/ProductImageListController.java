package spaland.api.productImageList.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.productImageList.service.IProductImageListService;
import spaland.api.productImageList.vo.RequestProductImageList;

@RestController
@RequestMapping("/api/v1/product/imageList")
@Slf4j
@RequiredArgsConstructor
public class ProductImageListController {

    private final IProductImageListService iProductImageListService;

    @PostMapping
    public ResponseEntity<Message> addProductImageList(@RequestBody RequestProductImageList requestProductImageList) {
        return iProductImageListService.addProductImageList(requestProductImageList);
    }
    /*
    @Todo
    이 메서드의 경우 response를 만들어야하는지?
     */
    @GetMapping("/{productImageListId}")
    public ResponseEntity<Message> getProductImageList(@PathVariable(value = "productImageListId") Long productImageListId) {
        return iProductImageListService.getProductImageList(productImageListId);
    }

    @GetMapping("/all/{productId}")
    public ResponseEntity<Message> getProductImageListByProductId(@PathVariable(value = "productId") Long productId) {
        return iProductImageListService.getProductImageListByProductId(productId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllProductImageList() {
        return iProductImageListService.getAllProductImageList();
    }
}
