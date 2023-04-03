package spaland.giftCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.service.IUserGiftCardService;
import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;
import spaland.giftCard.vo.ResponseGiftCard;

import java.util.List;

@RestController
@RequestMapping("/api/v1/UserGiftCardList")
@RequiredArgsConstructor
public class UserGiftCardController {

    private final IUserGiftCardService iUserGiftCardService;

    @PostMapping("/add") // 유저가 카드를 등록
    public void addGiftCard(@RequestBody RequestUserGiftCard requestUserGiftCard) {
        iUserGiftCardService.addGiftCardByUser(requestUserGiftCard);
    }

    @PutMapping("/charge") // 유저가 자신의 카드 중 하나에 충전
    public void chargeGiftCard(@RequestBody RequestChargeUserGiftCard requestChargeUserGiftCard) {
        iUserGiftCardService.chargeUsersGiftCard(requestChargeUserGiftCard);
    }

    @GetMapping("/get") // 유저가 들고 있는 카드 중 하나 조회
    public ResponseGiftCard getGiftCardByUser(@RequestBody RequestUserGiftCard requestUserGiftCard) {
        return iUserGiftCardService.getGiftCardByUser(requestUserGiftCard);
    }

    @GetMapping("/get/all/{userId}") // 유저가 들고 있는 카드 전부 조회
    public List<ResponseGiftCard> getAllGiftCardByUser(@PathVariable Long userId) {
        return iUserGiftCardService.getAllGiftCardByUser(userId);
    }
}