package spaland.products.service;

import spaland.products.model.ProductImage;
import spaland.products.vo.RequestProductImage;
import spaland.products.vo.ResponseProductImage;

import java.util.List;

public interface IProductImageService {
    void addProductImage(RequestProductImage requestProductImage);
    ResponseProductImage getProductImage(Long productImageId);
    List<ResponseProductImage> getAllProductImage();
}
