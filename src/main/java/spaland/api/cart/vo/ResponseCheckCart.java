package spaland.api.cart.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCheckCart {
    private Long id;
    private Long productId;
    private String name;
    private Integer price;
    private String titleImg;
    private Integer productAmount;
    private Integer frozen;
    private Boolean checkbox;
}
