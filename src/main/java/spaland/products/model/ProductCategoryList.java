package spaland.products.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Category category;
}
