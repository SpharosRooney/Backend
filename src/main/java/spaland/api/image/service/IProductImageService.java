package spaland.api.image.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.image.vo.RequestProductImage;

public interface IProductImageService {
    ResponseEntity<Message> addProductImage(RequestProductImage requestProductImage);
    ResponseEntity<Message> getProductImage(Long productImageId);
    ResponseEntity<Message> getAllProductImage();
}
