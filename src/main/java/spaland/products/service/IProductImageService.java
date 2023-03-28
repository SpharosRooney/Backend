package spaland.products.service;

import spaland.products.model.ProductImage;
import spaland.products.vo.RequestProductImage;

import java.util.List;

public interface IProductImageService {
    void addProductImage(RequestProductImage requestProductImage);
    ProductImage getProductImage(Long productImageId);
    List<ProductImage> getAllProductImage();
}
