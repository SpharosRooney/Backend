package spaland.products.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private Integer id;
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
