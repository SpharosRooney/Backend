package spaland.products.vo;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {

    private String description; // 상품 설명
    @Column(nullable = false)
    private String name; // 상품명
    private Integer price; // 가격
    private Integer inventory; // 재고
    private String titleImg; // 타이틀 이미지
    private Integer frozen; // 냉동 상품
    private Long salesQuantity; //판매수량
}
