package spaland.zincomplete.giftCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.zincomplete.giftCard.model.GiftCard;
import spaland.zincomplete.giftCard.vo.RequestAddGiftCard;

public interface IGiftCardRepository extends JpaRepository<GiftCard, Long> {

    GiftCard save(RequestAddGiftCard giftCard);
}