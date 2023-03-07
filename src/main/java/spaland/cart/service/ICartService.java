package spaland.cart.service;

import spaland.cart.model.Cart;
import spaland.cart.vo.RequestCart;
import spaland.cart.vo.RequestCartCount;
import spaland.cart.vo.RequestDeleteCart;
import spaland.products.model.Product;

import java.util.List;

public interface ICartService {
    Cart addCart(RequestCart requestCart);

    Product getByProductId(Long productId);

    List<Cart> getByUserId(Long userId);
    void modifyCart(RequestCartCount requestCartCount);

    void deleteProduct(RequestDeleteCart requestDeleteCart);
}
