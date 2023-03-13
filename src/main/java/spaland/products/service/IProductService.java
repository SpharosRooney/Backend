package spaland.products.service;

import spaland.products.model.Product;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

public interface IProductService {

    ResponseProduct addProduct(RequestProduct requestProduct);
    Product getProduct(Long productId);
    List<Product> getAllProduct();

    Product purchase(Long productId, int productNum);
    
}
