package spaland.cart.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.products.model.Product;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    private Integer productAmount;


}
