package spaland.giftCard.service;

import spaland.giftCard.model.GiftCard;
import spaland.giftCard.vo.RequestAddGiftCard;
import spaland.giftCard.vo.ResponseGiftCard;

import java.util.List;

public interface IGiftCardService {

    void addGiftCard(RequestAddGiftCard requestAddGiftCard);

    ResponseGiftCard getGiftCardInfo(Long id);

    List<ResponseGiftCard> getAllGiftCard();
}
