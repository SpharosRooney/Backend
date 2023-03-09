package spaland.shipping.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.users.model.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String detailAddress;
    private Boolean isUse;
    @Column(nullable = false)
    private String shippingPhone;

}
