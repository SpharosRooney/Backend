package spaland.products.service_v2;

import org.springframework.data.jpa.domain.Specification;
import spaland.products.model.Product;
import spaland.products.model_v2.Filter;
import spaland.products.vo_v2.*;

import java.util.List;

public interface IFilterServiceV2 {

    void addSeason(RequestSeasonV2 requestSeasonV2);

    void addCategory(RequestCategoryV2 requestCategoryV2);

    void addVolume(RequestVolumeV2 requestVolumeV2);

    void addTitle(RequestTitleV2 requestTitleV2);

    List<Product> findAllWithFilter(Specification<Filter> spec);

    List<Product> findAllWithSearch(String name);
}
