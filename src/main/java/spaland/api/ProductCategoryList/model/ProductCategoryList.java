package spaland.api.ProductCategoryList.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.api.event.model.Event;
import spaland.api.filter.model.CategoryLarge;
import spaland.api.filter.model.CategoryMiddle;
import spaland.api.filter.model.ProductOption;
import spaland.api.filter.model.ProductSeason;
import spaland.api.products.model.Product;
import spaland.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryList extends BaseTimeEntity{

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
