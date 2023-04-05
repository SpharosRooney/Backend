package spaland.zincomplete.coupon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spaland.zincomplete.coupon.model.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {

    Object findByName(String name);
}
