package spaland.products.service;

import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.vo.RequestCategoryList;
import spaland.products.vo.ResponseProduct;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestCategoryList requestCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
    List<ResponseProduct> getByCategoryLargeId(Integer categoryLargeId);
    List<ResponseProduct> getByCategoryMiddleId(Integer categoryMiddleId);
    List<ResponseProduct> getByProductOptionId(Integer productOptionId);
    List<ResponseProduct> getByEventId(Integer eventId);

}
