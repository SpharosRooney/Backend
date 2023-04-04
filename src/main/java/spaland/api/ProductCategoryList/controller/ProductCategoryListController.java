package spaland.api.ProductCategoryList.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.ProductCategoryList.service.IProductCategoryListService;
import spaland.api.ProductCategoryList.vo.RequestCategoryList;

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
    @GetMapping("/category/event/{seasonId}")
    public ResponseEntity<Message> getBySeasonId(@PathVariable(value = "seasonId") Integer seasonId) {
        return iProductCategoryListService.getByProductSeasonId(seasonId);
    }

}
