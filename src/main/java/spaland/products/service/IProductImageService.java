package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.ProductImage;
import spaland.products.vo.RequestProductImage;
import spaland.products.vo.ResponseProductImage;

import java.util.List;

public interface IProductImageService {
    ResponseEntity<Message> addProductImage(RequestProductImage requestProductImage);
    ResponseEntity<Message> getProductImage(Long productImageId);
    ResponseEntity<Message> getAllProductImage();
}
