package spaland.coupon.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCoupon{
    private String status;
    private Integer percent;
    private String name;
    private boolean isUse;
}
