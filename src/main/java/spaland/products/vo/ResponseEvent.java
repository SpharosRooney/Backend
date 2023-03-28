package spaland.products.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private String name;
    private String description;
    private String imgUrl;
    private String imgAlt;
    private Date startDate;
    private Date endDate;
    private Integer discountRate;
    private Boolean isDisplay;
}
