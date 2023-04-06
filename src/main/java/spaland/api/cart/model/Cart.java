package spaland.api.cart.model;

import lombok.*;
import spaland.api.products.model.Product;
import spaland.api.users.model.User;
import spaland.utility.BaseTimeEntity;

import jakarta.persistence.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Integer productAmount;

    @Builder.Default private Boolean isDelete = false;
    @Builder.Default private Boolean checkbox = false;

}
