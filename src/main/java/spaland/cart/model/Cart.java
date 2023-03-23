package spaland.cart.model;

import lombok.*;
import spaland.products.model.Product;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;

import jakarta.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
//@DynamicInsert
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer productAmount;

    @Builder.Default private Boolean isDelete = false;

}
