package spaland.coupon.service;

import spaland.coupon.model.Coupon;
import spaland.coupon.vo.RequestCoupon;
import spaland.coupon.vo.ResponseCoupon;

import java.util.List;

public interface ICouponService {

    ResponseCoupon addCoupon(RequestCoupon requestCoupon);

    ResponseCoupon getCoupon(Long couponId);

    List<ResponseCoupon> getAll();

    ResponseCoupon useCoupon(Long couponId);

    ResponseCoupon refundCoupon(Long couponId);
}
