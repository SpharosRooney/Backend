package spaland.zincomplete.coupon.service;

import spaland.zincomplete.coupon.vo.RequestAddCoupon2;
import spaland.zincomplete.coupon.vo.ResponseCoupon2;

import java.util.List;

public interface ICoupon2Service {

    ResponseCoupon2 addCouponByAdmin(RequestAddCoupon2 requestAddCoupon2);

    ResponseCoupon2 getCoupon(Long couponId);

    List<ResponseCoupon2> getAll(Long userId);

    ResponseCoupon2 useCoupon(Long couponId);

    ResponseCoupon2 refundCoupon(Long couponId);

//    List<UserCouponList> getAllbyUserId(Long userId);

}
