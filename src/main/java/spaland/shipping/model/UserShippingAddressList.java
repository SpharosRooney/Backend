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
public class UserShippingAddressList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ShippingAddress shippingAddress;

    @ManyToOne
    private User user;

}
