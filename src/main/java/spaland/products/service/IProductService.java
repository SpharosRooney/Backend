package spaland.products.service;

import spaland.products.model.Product;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

public interface IProductService {

    ResponseProduct addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<ResponseProduct> getAllProduct();

//    Product purchase(Long productId, int productNum);
    
}
