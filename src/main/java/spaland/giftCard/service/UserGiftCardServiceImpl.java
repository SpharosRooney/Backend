package spaland.giftCard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.giftCard.repository.IGiftCardRepository;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.model.UserGiftCard;
import spaland.giftCard.repository.IUserGiftCardRepository;
import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;
import spaland.users.repository.IUserRespository;
import spaland.utility.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserGiftCardServiceImpl implements IUserGiftCardService {

    private final IUserRespository iUserRespository;
    private final IGiftCardRepository iGiftCardRepository;
    private final IUserGiftCardRepository iUserGiftCardRepository;

    @Override
    public void addGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {

        iUserGiftCardRepository.save(UserGiftCard.builder()
                .user(iUserRespository.findById(requestUserGiftCard.getUserId()).get())
                .giftCard(iGiftCardRepository.findById(requestUserGiftCard.getCardId()).get())
                .build());
    }

    @Override
    public void chargeUsersGiftCard(RequestChargeUserGiftCard requestChargeUserGiftCard) {
        UserGiftCard userGiftCard = iUserGiftCardRepository.findByUserIdAndGiftCardId(requestChargeUserGiftCard.getUserId(), requestChargeUserGiftCard.getCardId());
        GiftCard giftCard=userGiftCard.getGiftCard();
        giftCard.setRemaining(giftCard.getRemaining()+requestChargeUserGiftCard.getAmount());
        userGiftCard.setGiftCard(giftCard);
        iUserGiftCardRepository.save(userGiftCard);


    }

    @Override
    public GiftCard getGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {
        UserGiftCard userGiftCard = iUserGiftCardRepository.findByUserIdAndGiftCardId(requestUserGiftCard.getUserId(), requestUserGiftCard.getCardId());
        return userGiftCard.getGiftCard();
    }

    @Override
    public List<GiftCard> getAllGiftCardByUser(Long userId) {
        List<UserGiftCard> userCardList = iUserGiftCardRepository.findAllByUserId(userId);
        List<GiftCard> giftCardList = new ArrayList<>();
        for (int i = 0; i < userCardList.size(); i++) { //여기를 좀 더 간단하게 할 수 없는지?
            giftCardList.add(userCardList.get(i).getGiftCard());
        }
        return giftCardList;
    }
}