package spaland.coupon.service;

import spaland.coupon.model.Coupon;
import spaland.coupon.vo.RequestCoupon;

import java.util.List;

public interface ICouponService {

    void addCoupon(RequestCoupon requestCoupon);

    Coupon getCoupon(Long couponId);

    List<Coupon> getAll();

}
