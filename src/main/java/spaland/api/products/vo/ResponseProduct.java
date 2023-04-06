package spaland.api.products.vo;

import lombok.*;
import spaland.api.image.model.ProductImage;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer inventory;
    private String titleImg;
    private List<ProductImage> productImageList;
    private Integer frozen;
    private Long salesQuantity;
    private Boolean isNew;
}
