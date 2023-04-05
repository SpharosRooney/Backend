package spaland.zincomplete.giftCard.service;

import spaland.zincomplete.giftCard.vo.RequestAddGiftCard;
import spaland.zincomplete.giftCard.vo.ResponseGiftCard;

import java.util.List;

public interface IGiftCardService {

    void addGiftCard(RequestAddGiftCard requestAddGiftCard);

    ResponseGiftCard getGiftCardInfo(Long id);

    List<ResponseGiftCard> getAllGiftCard();
}
