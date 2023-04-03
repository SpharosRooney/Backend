package spaland.zincomplete.wish.model;


import jakarta.persistence.*;
import lombok.*;
import spaland.api.products.model.Product;
import spaland.api.users.model.User;
import spaland.utility.BaseTimeEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

    @Builder.Default
    private Boolean isDelete = false;

}
