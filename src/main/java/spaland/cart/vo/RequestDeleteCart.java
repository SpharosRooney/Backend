package spaland.cart.vo;

import lombok.Getter;
import lombok.Setter;
import spaland.cart.model.Cart;

import java.util.List;

@Getter
@Setter
public class RequestDeleteCart {
//    private List<Cart> CartId;
    private Long id;
    private Boolean isDelete;
}
