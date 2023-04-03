package spaland.api.history.vo;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestHistory {


    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long userShippingAddressId;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String paymentType;
}
