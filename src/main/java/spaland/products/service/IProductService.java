package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.Product;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

public interface IProductService {

    ResponseEntity<Message> addProduct(RequestProduct requestProduct);
    ResponseEntity<Message> getProduct(Long productId);
    ResponseEntity<Message> getAllProduct();
}
