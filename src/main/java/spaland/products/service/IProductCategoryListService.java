package spaland.products.service;

import spaland.products.model.ProductCategoryList;
import spaland.products.vo.RequestCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestCategoryList requestCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
    List<ProductCategoryList> getByCategoryLargeId(Long categoryLargeId);
    List<ProductCategoryList> getByCategoryMiddleId(Long categoryMiddleId);
    List<ProductCategoryList> getByProductOptionId(Long productOptionId);
    List<ProductCategoryList> getByEventId(Long eventId);

}
