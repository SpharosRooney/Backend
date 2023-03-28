package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.model.ProductImageList;
import spaland.products.service.IProductImageListService;
import spaland.products.vo.RequestProductImageList;
import spaland.products.vo.ResponseProductImageList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/imageList")
@Slf4j
@RequiredArgsConstructor
public class ProductImageListController {

    private final IProductImageListService iProductImageListService;

    @PostMapping
    public void addProductImageList(@RequestBody RequestProductImageList requestProductImageList) {
        iProductImageListService.addProductImageList(requestProductImageList);
    }

    @GetMapping("/{productImageListId}")
    public ProductImageList getProductImageList(@PathVariable Long productImageListId) {
        return iProductImageListService.getProductImageList(productImageListId);
    }

    @GetMapping("/all/{productId}")
    public List<ResponseProductImageList> getProductImageListByProductId(@PathVariable Long productId) {
        return iProductImageListService.getProductImageListByProductId(productId);
    }

    @GetMapping("/all")
    public List<ProductImageList> getAllProductImageList() {
        return iProductImageListService.getAllProductImageList();
    }
}
