package spaland.products.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestProductSeason {

    @Column(nullable = false)
    private  String name;
}
