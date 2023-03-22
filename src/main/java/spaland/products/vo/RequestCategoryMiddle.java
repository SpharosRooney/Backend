package spaland.products.vo;

import javax.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestCategoryMiddle {

    @Column(nullable = false)
    private  String name;
}
