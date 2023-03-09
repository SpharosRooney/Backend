package spaland.shipping.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEditUserShippingAddress {

    private Long id;
    private String zipCode;
    private String address;
    private String detailAddress;
    private Boolean isUse;
    private String shippingPhone;
}
