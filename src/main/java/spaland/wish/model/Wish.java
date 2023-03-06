package spaland.wish.model;


import jakarta.persistence.*;
import lombok.*;
import spaland.products.model.Product;
import spaland.users.model.User;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

}
