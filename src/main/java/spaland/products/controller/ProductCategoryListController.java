package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.service.IProductCategoryListService;
import spaland.products.vo.RequestCategoryList;
import spaland.products.vo.ResponseProduct;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/categoryList")
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryListController {

    private final IProductCategoryListService iProductCategoryListService;

    @PostMapping
    public void addProductCategoryList(@RequestBody RequestCategoryList requestCategoryList) {
        iProductCategoryListService.addProductCategoryList(requestCategoryList);
    }

    @GetMapping("/category/large/{categoryLargeId}")
    public List<Product> getByCategoryLargeId(@PathVariable Integer categoryLargeId) {
        return iProductCategoryListService.getByCategoryLargeId(categoryLargeId);
    }
    @GetMapping("/category/middle/{categoryMiddleId}")
    public List<Product> getByCategoryMiddleId(@PathVariable Integer categoryMiddleId) {
        return iProductCategoryListService.getByCategoryMiddleId(categoryMiddleId);
    }
    @GetMapping("/category/option/{optionId}")
    public List<Product> getByOptionId(@PathVariable Integer optionId) {
        return iProductCategoryListService.getByProductOptionId(optionId);
    }
//    @GetMapping("/category/event/{eventId}")
//    public List<Product> getByEventId(@PathVariable Integer eventId) {
//        return iProductCategoryListService.getByEventId(eventId);
//    }

}
