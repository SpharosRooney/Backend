package spaland.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseGetUserCart {
    private Long id;
    private Long productId;
    private Long userId;
    private Integer productAmount;
}
