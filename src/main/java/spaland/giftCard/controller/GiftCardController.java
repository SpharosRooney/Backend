package spaland.giftCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftCard.service.IGiftCardService;
import spaland.giftCard.model.GiftCard;

import java.util.List;

@RestController
@RequestMapping("/giftCard")
@RequiredArgsConstructor
public class GiftCardController {

    private final IGiftCardService iGiftCardService;

    @PostMapping("/add")
    public void addGiftCard(@RequestBody GiftCard giftCard) {
        iGiftCardService.addGiftCard(giftCard);
    }

    @GetMapping("/get/{giftCardId}")
    public GiftCard getGiftCardInfo(@PathVariable Long giftCardId) {
        return iGiftCardService.getGiftCardInfo(giftCardId);
    }

    @GetMapping("/get/all")
    public List<GiftCard> getAllCard(){
        return iGiftCardService.getAllGiftCard();
    }
}