package spaland.coupon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.coupon.model.Coupon;
import spaland.coupon.service.ICouponService;
import spaland.coupon.vo.RequestCoupon;
import spaland.coupon.vo.ResponseCoupon;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coupon")
@RequiredArgsConstructor
@Slf4j
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/add")
    public ResponseCoupon addCoupon(@RequestBody RequestCoupon requestCoupon) {
        return iCouponService.addCoupon(requestCoupon); }

    @GetMapping("/get/{couponId}")
    public ResponseCoupon getCoupon(@PathVariable Long couponId) {return iCouponService.getCoupon(couponId);}

    @GetMapping("/get/all")
    public List<ResponseCoupon> getAllCoupon() {return iCouponService.getAll();}

    @PutMapping("/use/{couponId}")
    public ResponseCoupon useCoupon(@PathVariable Long couponId) {
        return iCouponService.useCoupon(couponId);
    }

    @PutMapping("/refund/{couponId}")
    public ResponseCoupon refundCoupon(@PathVariable Long couponId) {
        return iCouponService.refundCoupon(couponId);
    }
}
