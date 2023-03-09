package spaland.giftCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftCard.service.IGiftCardService;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.vo.RequestAddGiftCard;
import spaland.giftCard.vo.ResponseGiftCard;

import java.util.List;

@RestController
@RequestMapping("/giftCard")
@RequiredArgsConstructor
public class GiftCardController {

    private final IGiftCardService iGiftCardService;

    @PostMapping("/add") // 카드 추가 (종류)
    public void addGiftCard(@RequestBody RequestAddGiftCard requestAddGiftCard) {
        iGiftCardService.addGiftCard(requestAddGiftCard);
    }

    @GetMapping("/get/{giftCardId}") // 존재하는 카드 중 1개 보기
    public ResponseGiftCard getGiftCardInfo(@PathVariable Long giftCardId) {
        return iGiftCardService.getGiftCardInfo(giftCardId);
    }

    @GetMapping("/get/all") // 카드 전부 보기
    public List<ResponseGiftCard> getAllCard(){
        return iGiftCardService.getAllGiftCard();
    }
}