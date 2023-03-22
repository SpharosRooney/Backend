package spaland.products.vo;

import javax.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestCategory {

    // 필드명을 다르게 하면 주고 받을 때 매핑이 안된다.
    @Column(nullable = false)
    private  String name;
    private  String type;
}
