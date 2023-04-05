package spaland.api.cart.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCheckCart {
    private Long id;
    private Long productId;
    private String name; // 상품명
    private Integer price; // 가격
    private String titleImg; // 타이틀 이미지
    private Integer productAmount;
    private Integer frozen;
    private Boolean checkbox;
}
