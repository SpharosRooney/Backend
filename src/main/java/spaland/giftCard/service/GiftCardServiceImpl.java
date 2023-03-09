package spaland.giftCard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.giftCard.repository.IGiftCardRepository;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.vo.RequestAddGiftCard;
import spaland.giftCard.vo.ResponseGiftCard;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftCardServiceImpl implements IGiftCardService {

    private final IGiftCardRepository iGiftCardRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void addGiftCard(RequestAddGiftCard requestAddGiftCard) {
        iGiftCardRepository.save(modelMapper.map(requestAddGiftCard, GiftCard.class));
    }

    @Override
    public ResponseGiftCard getGiftCardInfo(Long giftCardId) {
        return modelMapper.map(iGiftCardRepository.findById(giftCardId).get(), ResponseGiftCard.class);
    }

    @Override
    public List<ResponseGiftCard> getAllGiftCard() {
        List<GiftCard> giftCardList = iGiftCardRepository.findAll();
        List<ResponseGiftCard> responseGiftCardList = new ArrayList<>();

        for (int i = 0; i < giftCardList.size(); i++) {
            responseGiftCardList.add(modelMapper.map(giftCardList.get(i), ResponseGiftCard.class));
        }
        return responseGiftCardList;
    }
}