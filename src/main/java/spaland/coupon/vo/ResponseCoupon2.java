package spaland.coupon.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCoupon2 {
    private Long userId;
    private String status;
    private Integer percent;
    private String name;
    private boolean isUse;
}
