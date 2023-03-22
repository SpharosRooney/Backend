package spaland.giftCard.vo;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAddGiftCard {

    private Integer price;
    private Integer remaining;
    private String img_root;
    private String type;
}
