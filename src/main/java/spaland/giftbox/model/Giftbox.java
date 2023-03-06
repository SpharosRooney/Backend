package spaland.giftbox.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.products.model.Product;
import spaland.users.model.User;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Giftbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer price;
    private String sender;
    private String description;
    private Integer giftAmount;
    private String letter;

}
