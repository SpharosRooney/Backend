package spaland.history.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.products.model.Product;
import spaland.shipping.model.UserShippingAddress;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String historyNum;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product; // 만약 단종되면 못찾아가게 해야함.

    @ManyToOne
    private UserShippingAddress userShippingAddress;
//    @Column(nullable = false)
//    private String zipCode;
//    @Column(nullable = false)
//    private String address;
//    @Column(nullable = false)
//    private String detailAddress;
//    @Column(nullable = false)
//    private String shippingPhone;

    @Column(nullable = false)
    private Long currentState;

    @Column
    private Integer amount;

    @Column(nullable = false)
    private String paymentType;
}
