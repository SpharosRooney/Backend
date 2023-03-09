package spaland.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.cart.model.Cart;
import spaland.cart.vo.ResponseGetUserCart;
import spaland.products.model.Product;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    Product findAllByProductId(Long productId);
    List<Cart> findAllByUserId(Long userId);
    List<Cart> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);

}
