package spaland.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.coupon.service.ICoupon2Service;
import spaland.coupon.vo.RequestAddCoupon2;
import spaland.coupon.vo.ResponseCoupon;
import spaland.coupon.vo.ResponseCoupon2;

import java.util.List;

@RestController
@RequestMapping("/v2/api/coupon") // 관리자 버전
@RequiredArgsConstructor
public class Coupon2Controller {

    private final ICoupon2Service iUserCouponListService;

    @PostMapping("/add") // 관리자로 부터 쿠폰 발급
    public ResponseCoupon2 addCouponByAdmin(@RequestBody RequestAddCoupon2 requestAddCoupon2) {
        return iUserCouponListService.addCouponByAdmin(requestAddCoupon2);
    }

    @GetMapping("/get/{couponId}")
    public ResponseCoupon2 getCoupon(@PathVariable Long couponId) {return iUserCouponListService.getCoupon(couponId);}

    @GetMapping("/get/all/{userId}")
    public List<ResponseCoupon2> getAllCoupon(@PathVariable Long userId) {return iUserCouponListService.getAll(userId);}

    @PutMapping("/use/{couponId}")
    public ResponseCoupon2 useCoupon(@PathVariable Long couponId) {
        return iUserCouponListService.useCoupon(couponId);
    }

    @PutMapping("/refund/{couponId}")
    public ResponseCoupon2 refundCoupon(@PathVariable Long couponId) {
        return iUserCouponListService.refundCoupon(couponId);
    }

}
