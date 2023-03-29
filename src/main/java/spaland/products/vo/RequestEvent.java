package spaland.products.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RequestEvent {
    private String name;
    private String description;
    private String imgUrl;
    private String imgAlt;
    private Date startDate;
    private Date endDate;
    private Integer discountRate;
    private Boolean isDisplay;
}
