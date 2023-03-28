package spaland.products.service;

import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.vo.RequestCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestCategoryList requestCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
    List<Product> getByCategoryLargeId(Integer categoryLargeId);
    List<Product> getByCategoryMiddleId(Integer categoryMiddleId);
    List<Product> getByProductOptionId(Integer productOptionId);
//    List<Product> getByEventId(Integer eventId);

}
