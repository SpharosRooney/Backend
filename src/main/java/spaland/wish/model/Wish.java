package spaland.wish.model;


import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import spaland.products.model.Product;
import spaland.users.model.User;
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
