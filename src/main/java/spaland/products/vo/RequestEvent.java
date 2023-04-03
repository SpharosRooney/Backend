package spaland.products.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RequestEvent {
    private String name;
    private String description;
    private String imgUrl;
    private String imgAlt;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date endDate;
    private Integer discountRate;
    private Boolean isDisplay;
}
