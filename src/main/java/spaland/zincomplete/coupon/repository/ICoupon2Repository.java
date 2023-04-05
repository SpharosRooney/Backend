package spaland.zincomplete.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.zincomplete.coupon.model.Coupon2;

import java.util.List;

public interface ICoupon2Repository extends JpaRepository<Coupon2, Long> {

    List<Coupon2> findAllByUserId(Long userId);
}

