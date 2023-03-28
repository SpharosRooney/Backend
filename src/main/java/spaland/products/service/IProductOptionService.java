package spaland.products.service;

import spaland.products.model.ProductOption;

import java.util.List;

public interface IProductOptionService {
    void addProductOption(ProductOption productOption);
    ProductOption getProductOption(Integer productOptionId);
    List<ProductOption> getAllProductOption();
}
