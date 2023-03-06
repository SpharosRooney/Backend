package spaland.coupon.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestUserCouponList {
    private Long userId;
    private Long couponId;
    private String couponUUID;
}
