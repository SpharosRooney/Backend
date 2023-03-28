package spaland.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import spaland.utility.BaseTimeEntity;

import java.util.Date;

@Entity
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String imgUrl;
    private String imgAlt;
    private Date startDate;
    private Date endDate;
    private Integer discountRate;
    private Boolean isDisplay;

}
