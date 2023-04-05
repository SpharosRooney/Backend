package spaland.zincomplete.coupon.service;

import spaland.zincomplete.coupon.vo.RequestCoupon;
import spaland.zincomplete.coupon.vo.ResponseCoupon;

import java.util.List;

public interface ICouponService {

    ResponseCoupon addCoupon(RequestCoupon requestCoupon);

    ResponseCoupon getCoupon(Long couponId);

    List<ResponseCoupon> getAll();

    ResponseCoupon useCoupon(Long couponId);

    ResponseCoupon refundCoupon(Long couponId);
}
