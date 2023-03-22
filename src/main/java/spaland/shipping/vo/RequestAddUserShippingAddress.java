package spaland.shipping.vo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestAddUserShippingAddress {
    @Column(nullable = false)
    private Long userId;
    private String zipCode;
    private String address;
    private String detailAddress;
    private Boolean isUse;
    private String shippingPhone;
}
