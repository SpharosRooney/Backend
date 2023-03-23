package spaland.products.vo_v2;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSeasonV2 {

    @Column(nullable = false)
    private String season;
}
