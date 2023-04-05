package spaland.api.history.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.api.products.model.Product;
import spaland.api.shipping.model.UserShippingAddress;
import spaland.api.users.model.User;
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
    private Product product;

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
