package spaland.history.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RequestHistory {

    @Column(nullable = false)
    private Long productId; // 상품 ID
    @Column(nullable = false)
    private Long userShippingAddress; // 배송지 ID;

    @Column(nullable = false)
    private Long amount; // 상품 개수

    @Column(nullable = false)
    private Long purchasePath; // 0이면 구매, 1이면 선물
    @Column(nullable = false)
    private String giftText;

    @Column(nullable = false)
    private String paymentType;
}
