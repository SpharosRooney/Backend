package spaland.coupon.model;

import javax.persistence.*;
import lombok.*;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon2 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String status = "사용 가능";
    private Integer percent;
    private String name;
    private boolean isUse = false;

}