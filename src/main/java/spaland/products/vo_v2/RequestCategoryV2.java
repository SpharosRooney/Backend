package spaland.products.vo_v2;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoryV2 {

    @Column(nullable = false)
    private String category;
}
