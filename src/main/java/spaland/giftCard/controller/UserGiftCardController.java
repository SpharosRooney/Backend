package spaland.giftCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftCard.model.GiftCard;
import spaland.giftCard.service.IUserGiftCardService;
import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;

import java.util.List;

@RestController
@RequestMapping("/UserGiftCardList")
@RequiredArgsConstructor
public class UserGiftCardController {

    private final IUserGiftCardService iUserGiftCardService;

    @PostMapping("/add")
    public void addGiftCard(@RequestBody RequestUserGiftCard requestUserGiftCard) {
        iUserGiftCardService.addGiftCardByUser(requestUserGiftCard);
    }

    @PutMapping("/charge")
    public void chargeGiftCard(@RequestBody RequestChargeUserGiftCard requestChargeUserGiftCard) {
        iUserGiftCardService.chargeUsersGiftCard(requestChargeUserGiftCard);
    }

    @GetMapping("/get")
    public GiftCard getGiftCardByUser(@RequestBody RequestUserGiftCard requestUserGiftCard) {
        return iUserGiftCardService.getGiftCardByUser(requestUserGiftCard);
    }

    @GetMapping("/get/all/{userId}")
    public List<GiftCard> getAllGiftCardByUser(@PathVariable Long userId) {
        return iUserGiftCardService.getAllGiftCardByUser(userId);
    }
}