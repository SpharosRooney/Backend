package spaland.cart.service;

import spaland.cart.model.Cart;
import spaland.cart.vo.*;
import spaland.products.model.Product;

import java.util.List;

public interface ICartService {
    Cart addCart(RequestCart requestCart);

    Product getByProductId(Long productId);

    void modifyCart(RequestCartCount requestCartCount);
    List<ResponseGetUserCart> getAllByUser(Long userId);
    void deleteProduct(RequestDeleteCart requestDeleteCart);
}
