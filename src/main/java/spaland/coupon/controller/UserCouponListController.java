package spaland.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.coupon.model.UserCouponList;
import spaland.coupon.service.IUserCouponListService;
import spaland.coupon.vo.RequestUserCouponList;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user-coupon")
@RequiredArgsConstructor
public class UserCouponListController {

    private final IUserCouponListService iUserCouponListService;

    @PostMapping("/add") // 관리자로 부터 쿠폰 발급
    public void addCouponByAdmin(@RequestBody RequestUserCouponList requestUserCouponList) {
        iUserCouponListService.addCouponByAdmin(requestUserCouponList);
    }

    @GetMapping("/get/{userId}")
    public List<UserCouponList> getAllByUserId(@PathVariable Long userId){
        return iUserCouponListService.getAllbyUserId(userId);
    }


}
