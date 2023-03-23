package spaland.coupon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spaland.coupon.model.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {

    Object findByName(String name);
}
