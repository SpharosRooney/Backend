package spaland.history.vo;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import spaland.users.model.User;

@Data
public class RequestHistory {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long userShippingAddress;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String paymentType;
}
