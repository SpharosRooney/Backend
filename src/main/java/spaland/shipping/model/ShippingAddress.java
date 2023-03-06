package spaland.shipping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spaland.utility.BaseTimeEntity;

@Getter
@Setter
@ToString
@Entity
public class ShippingAddress extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String post;
    private String address;
    private String detailAddress;
    private Short isUse;
    private String shippingPhone;


}
