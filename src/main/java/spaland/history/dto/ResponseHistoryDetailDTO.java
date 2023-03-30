package spaland.history.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseHistoryDetailDTO {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private String productTitleImg; // 타이틀 이미지

    private String zipCode;
    private String address;
    private String detailAddress;
    private String shippingPhone;

    private String paymentType;
    private Integer amount;

    private Integer totalPrice;

    private Long currentState;
    /*
     0 : 상품 준비중
     1 : 배송 준비중
     2 : 배송중
     3 : 배송완료
     4 : 취소완료
     */
}
