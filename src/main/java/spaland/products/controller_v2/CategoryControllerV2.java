package spaland.products.controller_v2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import spaland.products.model.Product;
import spaland.products.model_v2.Filter;
import spaland.products.model_v2.CategorySpecificationV2;
import spaland.products.repository_v2.IFilterRepositoryV2;
import spaland.products.service_v2.IFilterServiceV2;
import spaland.products.vo.RequestProduct;
import spaland.products.vo_v2.RequestCategoryV2;
import spaland.products.vo_v2.RequestSeasonV2;
import spaland.products.vo_v2.RequestTitleV2;
import spaland.products.vo_v2.RequestVolumeV2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v2/category")
@RequiredArgsConstructor
public class CategoryControllerV2 {

    //    private final IFilterRepositoryV2 iFilterRepositoryV2;
    private final IFilterServiceV2 iFilterServiceV2;

    @PostMapping("/add/season")
    public void addSeason(@RequestBody RequestSeasonV2 requestSeasonV2) {
        iFilterServiceV2.addSeason(requestSeasonV2);
    }

    @PostMapping("/add/category")
    public void addCategory(@RequestBody RequestCategoryV2 requestCategoryV2) {
        iFilterServiceV2.addCategory(requestCategoryV2);
    }

    @PostMapping("/add/volume")
    public void addVolume(@RequestBody RequestVolumeV2 requestVolumeV2) {
        iFilterServiceV2.addVolume(requestVolumeV2);
    }

    @PostMapping("/add/title")
    public void addTitle(@RequestBody RequestTitleV2 requestTitleV2) {
        iFilterServiceV2.addTitle(requestTitleV2);
    }

    @GetMapping("/filter")
    public List<Product> findAllWithFilter(
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) Long season,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long volume
    ) {
        Specification<Filter> spec = (root, query, criteriaBuilder) -> null;

        if (price != null) {
            spec = spec.and(CategorySpecificationV2.equalPrice(price));
        }
        if (season != null) {
            spec = spec.and(CategorySpecificationV2.equalSeason(season));
        }
        if (category != null) {
            spec = spec.and(CategorySpecificationV2.equalCategory(category));
        }
        if (volume != null) {
            spec = spec.and(CategorySpecificationV2.equalVolume(volume));
        }

        return iFilterServiceV2.findAllWithFilter(spec);
    }

    @GetMapping("/search")
    public List<Product> findAllWithSearch(@RequestParam String name){
        return iFilterServiceV2.findAllWithSearch(name);
    }
}
