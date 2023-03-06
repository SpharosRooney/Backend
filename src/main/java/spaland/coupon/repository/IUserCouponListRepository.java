package spaland.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.coupon.model.UserCouponList;

import java.util.List;

public interface IUserCouponListRepository extends JpaRepository<UserCouponList, Long> {

    List<UserCouponList> findAllByUserId(Long userId);
}

