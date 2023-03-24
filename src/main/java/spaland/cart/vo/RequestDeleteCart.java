package spaland.cart.vo;

import lombok.Getter;
import lombok.Setter;
import spaland.cart.model.Cart;

import java.util.List;

@Getter
@Setter
public class RequestDeleteCart {

    private Long id;
    private Boolean isDelete;
}
