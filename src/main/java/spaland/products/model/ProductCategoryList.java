package spaland.products.model;

import jakarta.persistence.*;
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
public class ProductCategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private CategoryLarge categoryLarge;
    @ManyToOne
    private CategoryMiddle categoryMiddle;
    @ManyToOne
    private ProductOption productOption;
    @ManyToOne
    private ProductSeason productSeason;
    @ManyToOne
    private Event event;

}
