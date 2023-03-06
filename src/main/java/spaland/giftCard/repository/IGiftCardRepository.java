package spaland.giftCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftCard.model.GiftCard;

public interface IGiftCardRepository extends JpaRepository<GiftCard, Long> {

    GiftCard save(GiftCard giftCard);
}
