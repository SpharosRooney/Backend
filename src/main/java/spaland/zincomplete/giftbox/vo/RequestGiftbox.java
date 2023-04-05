package spaland.zincomplete.giftbox.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestGiftbox {
    private Long UserId;
    private Long ProductId;

    private String sender;
    private Integer state;
    private Integer giftAmount;
    private String letter;

    private Date recivedDate;
}
