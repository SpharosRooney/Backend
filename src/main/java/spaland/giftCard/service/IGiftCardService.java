package spaland.giftCard.service;

import spaland.giftCard.model.GiftCard;

import java.util.List;

public interface IGiftCardService {

    void addGiftCard(GiftCard giftCard);

    GiftCard getGiftCardInfo(Long id);

    List<GiftCard> getAllGiftCard();
}
