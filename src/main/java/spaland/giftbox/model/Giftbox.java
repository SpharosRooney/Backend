package spaland.giftbox.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import spaland.products.model.Product;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Giftbox extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private String sender;
    private String description;
    private Integer giftAmount;
    private String letter;


}
