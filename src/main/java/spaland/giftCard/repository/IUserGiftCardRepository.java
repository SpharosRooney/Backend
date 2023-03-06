package spaland.giftCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftCard.model.UserGiftCardList;

public interface IUserGiftCardRepository extends JpaRepository<UserGiftCardList, Long> {

    UserGiftCardList save(UserGiftCardList userGiftCardList);

//    GiftCard findByUserIdAndCardId(Long userId, Long cardId);

//    List<GiftCard> findAllByUserId(Long userId);
}
