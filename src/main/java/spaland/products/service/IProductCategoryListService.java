package spaland.products.service;

import spaland.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(Integer categoryId, Long productId);
    List<ProductCategoryList> getByProductId(Long productId);

}
