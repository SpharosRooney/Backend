package spaland.api.cart.vo;

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
    private String name;
    private Integer price;
    private String titleImg;
    private Integer productAmount;
    private Integer frozen;
    private Boolean checkbox;
}
