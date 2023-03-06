package spaland.cart.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCart {
    private Long UserId;
    private Long ProductId;
    private Integer productAmount;
}
