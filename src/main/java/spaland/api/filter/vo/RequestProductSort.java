package spaland.api.filter.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestProductSort {

    @Column(nullable = false)
    private  String name;
}
