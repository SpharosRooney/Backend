package spaland.history.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spaland.utility.BaseTimeEntity;

@Entity
@Setter
@Getter
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name; // 살품명

    @Column(nullable = false)
    private Integer productId; // 상품 일련 번호

    @Column(nullable = false)
    private String purchaseType; // 주문 유형

    private String couponName; // 사용 쿠폰

    @Column(nullable = false)
    private String paymentType; // 결제 수단

    @Column(nullable = false)
    private Integer totalPay; // 결제 금액

    @Column(nullable = false)
    private String recipient; // 수령인

    private String currentState; // 주문 상태

}
