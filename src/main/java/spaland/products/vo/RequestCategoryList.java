package spaland.products.vo;

import lombok.Data;

@Data
public class RequestCategoryList {

    private Long productId;
    private Integer categoryLargeId;
    private Integer categoryMiddleId;
    private Integer productOptionId;
    private Integer productSeasonId;
    private Integer eventId;

}
