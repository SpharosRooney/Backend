package spaland.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.cart.model.Cart;
import spaland.products.model.Product;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);

    List<Cart> findAllByUserId(Long userId);

    Product findAllByProductId(Long productId);


}
