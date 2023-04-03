package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
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
    public ResponseEntity<Message> addProductCategoryList(@RequestBody RequestCategoryList requestCategoryList) {
        return iProductCategoryListService.addProductCategoryList(requestCategoryList);
    }

    @GetMapping("/category/large/{categoryLargeId}")
    public ResponseEntity<Message> getByCategoryLargeId(@PathVariable(value = "categoryLargeId") Integer categoryLargeId) {
        return iProductCategoryListService.getByCategoryLargeId(categoryLargeId);
    }
    @GetMapping("/category/middle/{categoryMiddleId}")
    public ResponseEntity<Message> getByCategoryMiddleId(@PathVariable(value = "categoryMiddleId") Integer categoryMiddleId) {
        return iProductCategoryListService.getByCategoryMiddleId(categoryMiddleId);
    }
    @GetMapping("/category/option/{optionId}")
    public ResponseEntity<Message> getByOptionId(@PathVariable(value = "optionId") Integer optionId) {
        return iProductCategoryListService.getByProductOptionId(optionId);
    }
    @GetMapping("/category/event/{eventId}")
    public ResponseEntity<Message> getByEventId(@PathVariable(value = "eventId") Integer eventId) {
        return iProductCategoryListService.getByEventId(eventId);
    }

}
