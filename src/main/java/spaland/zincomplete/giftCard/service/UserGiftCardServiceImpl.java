package spaland.zincomplete.giftCard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.zincomplete.giftCard.repository.IGiftCardRepository;
import spaland.zincomplete.giftCard.model.GiftCard;
import spaland.zincomplete.giftCard.model.UserGiftCard;
import spaland.zincomplete.giftCard.repository.IUserGiftCardRepository;
import spaland.zincomplete.giftCard.vo.RequestChargeUserGiftCard;
import spaland.zincomplete.giftCard.vo.RequestUserGiftCard;
import spaland.zincomplete.giftCard.vo.ResponseGiftCard;
import spaland.api.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserGiftCardServiceImpl implements IUserGiftCardService {

    private final IUserRepository iUserRepository;
    private final IGiftCardRepository iGiftCardRepository;
    private final IUserGiftCardRepository iUserGiftCardRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void addGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {
        iUserGiftCardRepository.save(UserGiftCard.builder()
                .user(iUserRepository.findById(requestUserGiftCard.getUserId()).get())
                .giftCard(iGiftCardRepository.findById(requestUserGiftCard.getCardId()).get())
                .build());
    }

    @Override
    public void chargeUsersGiftCard(RequestChargeUserGiftCard requestChargeUserGiftCard) {
        UserGiftCard userGiftCard = iUserGiftCardRepository.findByUserIdAndGiftCardId(requestChargeUserGiftCard.getUserId(), requestChargeUserGiftCard.getCardId());
        GiftCard giftCard = userGiftCard.getGiftCard();
        giftCard.setRemaining(giftCard.getRemaining() + requestChargeUserGiftCard.getAmount());
        userGiftCard.setGiftCard(giftCard);
        iUserGiftCardRepository.save(userGiftCard);
    }

    @Override
    public ResponseGiftCard getGiftCardByUser(RequestUserGiftCard requestUserGiftCard) {
        UserGiftCard userGiftCard = iUserGiftCardRepository.findByUserIdAndGiftCardId(requestUserGiftCard.getUserId(), requestUserGiftCard.getCardId());

        return modelMapper.map(userGiftCard.getGiftCard(), ResponseGiftCard.class);
    }

    @Override
    public List<ResponseGiftCard> getAllGiftCardByUser(Long userId) {
        List<UserGiftCard> userCardList = iUserGiftCardRepository.findAllByUserId(userId);
        List<ResponseGiftCard> giftCardList = new ArrayList<>();
        for (int i = 0; i < userCardList.size(); i++) { //여기를 좀 더 간단하게 할 수 없는지?
            giftCardList.add(modelMapper.map(userCardList.get(i).getGiftCard(), ResponseGiftCard.class));
        }
        return giftCardList;
    }
}