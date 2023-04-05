package spaland.api.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.products.vo.RequestProduct;

public interface IProductService {

    ResponseEntity<Message> addProduct(RequestProduct requestProduct);
    ResponseEntity<Message> getProduct(Long productId);
    ResponseEntity<Message> getAllProduct();
}
