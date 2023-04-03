package spaland.zincomplete.giftCard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGiftCard {

    private Integer price;
    private Integer remaining;
    private String img_root;
    private String type;
}
