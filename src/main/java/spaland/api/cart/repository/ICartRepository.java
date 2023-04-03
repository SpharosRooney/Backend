package spaland.api.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.cart.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    List<Cart> findAllByUserId(Long userId);
    Optional<Cart> findByUserIdAndIsDeleteAndProductId(Long userId, Boolean isDelete, Long productId);
    Optional<Cart> findByIdAndIsDelete(Long id, Boolean isDelete);
    List<Cart> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);

}
