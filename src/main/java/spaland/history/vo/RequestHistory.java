package spaland.history.vo;

import javax.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestHistory {
    @Column(nullable = false)
    private String name; // 살품명

    @Column(nullable = false)
    private Integer productId; // 상품 일련 번호

    @Column(nullable = false)
    private String purchaseType; // 주문 유형

    @Column(nullable = false)
    private String paymentType; // 결제 수단

    private String couponName;

    @Column(nullable = false)
    private Integer totalPay; // 결제 금액

    @Column(nullable = false)
    private String recipient; // 수령인

    @Column(nullable = false)
    private String currentState; // 주문 상태

}
