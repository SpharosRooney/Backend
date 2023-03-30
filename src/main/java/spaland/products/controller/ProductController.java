package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import spaland.products.model.CategorySpecification;
import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.repository.ICategoryLargeRepository;
import spaland.products.service.IProductCategoryListService;
import spaland.products.service.IProductService;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;
    private final IProductCategoryListService iProductCategoryListService;

    private final ICategoryLargeRepository iCategoryLargeRepository;

    @PostMapping("/add")
    public void addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    public ResponseProduct getProduct(@PathVariable Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    public List<ResponseProduct> getAlLProduct() {
        return iProductService.getAllProduct();
    }

    @GetMapping("/get") // 나중에 add get 이런거 다 제거?
    public List<Product> findAllProduct(
            @RequestParam(required = false) String keyword, // 검색키워드
            @RequestParam(required = false) String categoryLarge, // 대분류 (케이크, 텀블러/보온병, ...)
            @RequestParam(required = false) List<String> categoryMiddle, // 카테고리(롤케이크, ...)
            @RequestParam(required = false) String option, // 용량(volume) (short, tall, ...)
            @RequestParam(required = false) List<String> event, // 시즌 (Spring, 커티스쿨릭, ...)
            @RequestParam(required = false) String price // 가격 (1만원미만, 1만원대, ..)
//            @RequestParam(required = false) String sort // 정렬기준
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
            for(String iter:event) {
                spec = spec.and(CategorySpecification.equalEvent(iter)); //ok
            }
        }
        if (price != null) {
            spec = spec.and(CategorySpecification.equalPrice(price)); //ok
        }
//        if (sort != null) {
//            spec = spec.and(CategorySpecification.equalSort(sort));
//        }

        return iProductCategoryListService.findAllByFilter(spec);
    }
}