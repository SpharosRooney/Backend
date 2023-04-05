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
    private String name; // 상품명
    private Integer price; // 가격
    private String titleImg; // 타이틀 이미지
    private Integer productAmount;
    private Integer frozen;
    private Boolean checkbox;
}
