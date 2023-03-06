package spaland.coupon.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.utility.BaseTimeEntity;


@Entity
@Getter
@Setter
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Integer percent;
    private String name;

}
