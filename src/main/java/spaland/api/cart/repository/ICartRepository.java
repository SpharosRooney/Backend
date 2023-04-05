package spaland.api.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spaland.api.cart.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    List<Cart> findAllByUserId(Long userId);
    Optional<Cart> findByUserIdAndIsDeleteAndProductId(Long userId, Boolean isDelete, Long productId);
    Optional<Cart> findByIdAndIsDelete(Long id, Boolean isDelete);
    List<Cart> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);

    @Query(value = "SELECT c from Cart c join fetch c.user u join fetch c.product p where c.id = :id ")
    Optional<Cart> findLazyById(@Param("id") Long id);

}
