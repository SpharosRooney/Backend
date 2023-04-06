package spaland.api.products.vo;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {

    private String description;
    @Column(nullable = false)
    private String name;
    private Integer price;
    private Integer inventory;
    private String titleImg;
    private Integer frozen;
    private Long salesQuantity;
}
