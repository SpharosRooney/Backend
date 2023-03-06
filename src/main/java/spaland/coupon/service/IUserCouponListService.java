package spaland.coupon.service;

import spaland.coupon.model.UserCouponList;
import spaland.coupon.vo.RequestUserCouponList;

import java.util.List;

public interface IUserCouponListService {

    void addUserCouponList(RequestUserCouponList requestUserCouponList);

    List<UserCouponList> getAllbyUserId(Long userId);

}
