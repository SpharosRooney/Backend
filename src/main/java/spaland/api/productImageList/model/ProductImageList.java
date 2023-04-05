package spaland.api.productImageList.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.api.image.model.ProductImage;
import spaland.api.products.model.Product;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne
    private ProductImage productImage;

}
