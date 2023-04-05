package spaland.api.history.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseHistoryDTO {
    private Long productId;

    private String productName;

    private String productTitleImg;

    private Integer totalPrice;

    private Integer amount;

    private Long currentState;


}
