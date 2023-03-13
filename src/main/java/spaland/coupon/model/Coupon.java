package spaland.coupon.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.utility.BaseTimeEntity;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Integer percent;
    private String name;
    private boolean isUse = false;

}
