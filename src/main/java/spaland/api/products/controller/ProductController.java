package spaland.api.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.ProductCategoryList.model.ProductCategoryList;
import spaland.api.ProductCategoryList.service.IProductCategoryListService;
import spaland.api.products.service.IProductService;
import spaland.api.filter.service.CategorySpecification;
import spaland.api.products.vo.RequestProduct;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;
    private final IProductCategoryListService iProductCategoryListService;

    @PostMapping("/add")
    public ResponseEntity<Message> addProduct(@RequestBody RequestProduct requestProduct) {
        return iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Message> getProduct(@PathVariable(value = "productId") Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Message> getAlLProduct() {
        return iProductService.getAllProduct();
    }

    @GetMapping("/get")
    public ResponseEntity<Message> findAllProduct(
            @RequestParam(required = false) String keyword, // 검색키워드
            @RequestParam(required = false) String categoryLarge, // 대분류 (케이크, 텀블러/보온병, ...)
            @RequestParam(required = false) List<String> categoryMiddle, // 카테고리(롤케이크, ...)
            @RequestParam(required = false) String option, // 용량(volume) (short, tall, ...)
            @RequestParam(required = false) List<String> event, // 시즌 (Spring, 커티스쿨릭, ...)
            @RequestParam(required = false) String price, // 가격 (1만원미만, 1만원대, ..)
            @RequestParam(required = false) String sort // 추천순(=판매량순), 낮은가격순, 높은가격순, 신상품순
    ) {
        Specification<ProductCategoryList> spec = (root, query, criteriaBuilder) -> null;

        if (keyword != null) {
            spec = spec.and(CategorySpecification.equalKeyword(keyword)); //ok
        }
        if (categoryLarge != null) {
            spec = spec.and(CategorySpecification.equalCategoryLarge(categoryLarge)); //ok
        }
        if (categoryMiddle != null) {
            for (String iter : categoryMiddle) {
                spec = spec.and(CategorySpecification.equalCategoryMiddle(iter)); //ok
            }
        }
        if (option != null) {
            spec = spec.and(CategorySpecification.equalOption(option)); //ok
        }
        if (event != null) {
            for (String iter : event) {
                spec = spec.and(CategorySpecification.equalEvent(iter)); //ok
            }
        }
        if (price != null) {
            spec = spec.and(CategorySpecification.equalPrice(price)); //ok
        }
        if (sort != null) {
            spec = spec.and(CategorySpecification.applySort(sort));
        }

        return iProductCategoryListService.findAllByFilter(spec);
    }
}