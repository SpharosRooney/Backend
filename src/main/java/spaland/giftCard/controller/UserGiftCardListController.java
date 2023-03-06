package spaland.giftCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftCard.service.IGiftCardListService;
import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;

@RestController
@RequestMapping("/UserGiftCardList")
@RequiredArgsConstructor
public class UserGiftCardListController {

    private final IGiftCardListService iGiftCardListService;

    @PostMapping("/add")
    public void addGiftCard(@RequestBody RequestUserGiftCard requestUserGiftCard) {
        iGiftCardListService.addGiftCardByUser(requestUserGiftCard);
    }

    @PutMapping("/charge")
    public void chargeGiftCard(@RequestBody RequestChargeUserGiftCard requestChargeUserGiftCard) {
        iGiftCardListService.chargeUsersGiftCard(requestChargeUserGiftCard);
    }

//    @GetMapping("/get")
//    public GiftCard getGiftCardByUser(@RequestBody RequestUserGiftCard requestUserGiftCard) {
//        return iGiftCardListService.getGiftCardByUser(requestUserGiftCard);
//    }

//    @GetMapping("/get/all/{userId}")
//    public List<GiftCard> getAllGiftCardByUser(@PathVariable Long userId) {
//        return iGiftCardListService.getAllGiftCardByUser(userId);
//    }
}
