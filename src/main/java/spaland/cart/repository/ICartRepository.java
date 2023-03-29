package spaland.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.cart.model.Cart;
import spaland.cart.vo.ResponseGetUserCart;
import spaland.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    List<Cart> findAllByUserId(Long userId);
    Optional<Cart> findByUserIdAndIsDeleteAndProductId(Long userId, Boolean isDelete, Long productId);

    List<Cart> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);

}
