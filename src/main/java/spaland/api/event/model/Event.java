package spaland.api.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
