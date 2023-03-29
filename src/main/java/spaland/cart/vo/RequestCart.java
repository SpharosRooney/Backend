package spaland.cart.vo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCart {
    private Long productId;
    private Integer productAmount;
}
