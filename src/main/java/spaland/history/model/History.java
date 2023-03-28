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
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long purchaseType;
    private String gifText;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private String shippingStatus;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String recipient;

    private String currentState; // 주문 상태

}
