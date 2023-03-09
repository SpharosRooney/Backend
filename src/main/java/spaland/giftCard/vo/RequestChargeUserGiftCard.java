package spaland.giftCard.vo;

import lombok.Data;

@Data
public class RequestChargeUserGiftCard {

    private Long userId;
    private Long cardId;
    private Integer amount;
}