package spaland.zincomplete.coupon.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestAddCoupon2 {
    private Long userId;
    private String status; // 상태
    private Integer percent; // 할인률
    private String name; // 쿠폰 종류
}
