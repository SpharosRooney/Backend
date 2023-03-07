package spaland.giftCard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.giftCard.repository.IGiftCardRepository;
import spaland.giftCard.model.GiftCard;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftCardServiceImpl implements IGiftCardService {

    private final IGiftCardRepository iGiftCardRepository;

    @Override
    public void addGiftCard(GiftCard giftCard) {
        iGiftCardRepository.save(giftCard);
    }

    @Override
    public GiftCard getGiftCardInfo(Long giftCardId) {
        return iGiftCardRepository.findById(giftCardId).get();
    }

    @Override
    public List<GiftCard> getAllGiftCard() {
        return iGiftCardRepository.findAll();
    }


}