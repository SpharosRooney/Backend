package spaland.coupon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.coupon.model.Coupon;
import spaland.coupon.service.ICouponService;
import spaland.coupon.vo.RequestCoupon;

import java.util.List;

@RestController
@RequestMapping("/vi/api/coupon")
@RequiredArgsConstructor
@Slf4j
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/add")
    public void addCoupon(@RequestBody RequestCoupon requestCoupon) { iCouponService.addCoupon(requestCoupon); }

    @GetMapping("/get/{couponId}")
    public Coupon getCoupon(@PathVariable Long couponId) {return iCouponService.getCoupon(couponId);}

    @GetMapping("/get/all")
    public List<Coupon> getAllCoupon() {return iCouponService.getAll();}
}
