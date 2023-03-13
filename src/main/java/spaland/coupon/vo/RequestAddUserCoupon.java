package spaland.coupon.vo;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import spaland.coupon.model.Coupon;
import spaland.users.model.User;

@Data
public class RequestAddUserCoupon {

    private Long userId;
    private Long couponId;

}
