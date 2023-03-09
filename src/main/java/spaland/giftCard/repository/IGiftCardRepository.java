package spaland.giftCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.vo.RequestAddGiftCard;

public interface IGiftCardRepository extends JpaRepository<GiftCard, Long> {

    GiftCard save(RequestAddGiftCard giftCard);
}