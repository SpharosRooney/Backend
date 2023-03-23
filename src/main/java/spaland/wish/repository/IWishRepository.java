package spaland.wish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.Product;
import spaland.wish.model.Wish;

import java.util.List;

public interface IWishRepository extends JpaRepository<Wish,Long> {
    List<Wish> findAllByUserId(Long userId);
    List<Wish> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);
}
