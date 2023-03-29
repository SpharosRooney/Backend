package spaland.history.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spaland.products.model.Product;
import spaland.shipping.model.UserShippingAddress;
import spaland.utility.BaseTimeEntity;

@Entity
@Setter
@Getter
@Builder
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product; // 만약 단종되면 못찾아가게 해야함.
    @Column(nullable = false)
    private String productName; // 이름
    @Column(nullable = false)
    private Integer price; // 최초 productId를 통하여 가격 가져옴
    @Column(nullable = false)
    private Long amount; // 개수
    @Column(nullable = false)
    private Long total; // 최종가격
    @Column(nullable = false)
    private String titleImg;

    @ManyToOne
    private UserShippingAddress userShippingAddress;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String detailAddress;
    @Column(nullable = false)
    private String shippingPhone;

    @Column(nullable = false)
    private Long purchasePath; // 0이면 구매, 1이면 선물
    @Column(nullable = false)
    private String giftText;
    @Column(nullable = false)
    private Long currentState;
    @Column(nullable = false)
    private String historyNum;
    @Column(nullable = false)
    private String paymentType;
}
