package spaland.products.service;

import spaland.products.model.Product;
import spaland.products.vo.RequestProduct;

import java.util.List;

public interface IProductService {

    void addProduct(RequestProduct requestProduct);
    Product getProduct(Long productId);
    List<Product> getAllProduct();

    Product purchase(Long productId, int productNum);
    
}
