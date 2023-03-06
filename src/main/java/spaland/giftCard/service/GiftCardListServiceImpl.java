package spaland.giftCard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.giftCard.model.UserGiftCardList;
import spaland.giftCard.repository.IUserGiftCardRepository;
import spaland.giftCard.repository.IGiftCardRepository;
import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;
import spaland.users.repository.IUserRespository;
import spaland.utility.BaseTimeEntity;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftCardListServiceImpl extends BaseTimeEntity implements IGiftCardListService {

    private final IUserRespository iUserRespository;
    private final IGiftCardRepository iGiftCardRepository;
    private final IUserGiftCardRepository iUserGiftCardRepository;

    @Override
    public void addGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {

        iUserGiftCardRepository.save(UserGiftCardList.builder()
                .user(iUserRespository.findById(requestUserGiftCard.getUserId()).get())
                .giftCard(iGiftCardRepository.findById(requestUserGiftCard.getCardId()).get())
                .amount(iGiftCardRepository.findById(requestUserGiftCard.getCardId()).get().getPrice())
                .build());
    }

    @Override
    public void chargeUsersGiftCard(RequestChargeUserGiftCard requestChargeUserGiftCard) {
        UserGiftCardList userGiftCardList = iUserGiftCardRepository.findById(requestChargeUserGiftCard.getUserGiftCardListId()).get();
        userGiftCardList.setAmount(userGiftCardList.getAmount() + requestChargeUserGiftCard.getAmount());
        iUserGiftCardRepository.save(userGiftCardList);
    }

//    @Override
//    public GiftCard getGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {
////        List<GiftCard> cards = iUserGiftCardRepository.findAllGiftCardByUserId(userId).stream().map(UserGiftCardList::getGiftCard).collect(Collectors.toList());
////        return cards.stream().filter(c -> c.getId().equals(cardId)).findFirst().orElseThrow(()->new IllegalArgumentException());
//
//        return iUserGiftCardRepository.findByUserIdAndCardId(requestUserGiftCard.getUserId(), requestUserGiftCard.getCardId());
//
//    }

//    @Override
//    public List<GiftCard> getAllGiftCardByUser(Long userId) {
////        List<UserGiftCardList> allGiftCardByUserId = iUserGiftCardRepository.findAllGiftCardByUserId(userId);
////        return allGiftCardByUserId.stream().map(UserGiftCardList::getGiftCard).collect(Collectors.toList());
//
////        return iUserGiftCardRepository.findAllByUserId(userId);
//        return null;
//    }
}
