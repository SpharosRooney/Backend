package spaland.zincomplete.giftbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.zincomplete.giftbox.model.Giftbox;
import spaland.zincomplete.giftbox.service.IGiftboxService;
import spaland.zincomplete.giftbox.vo.RequestGiftbox;
import spaland.zincomplete.giftbox.vo.ResponseGetUserGiftbox;
import spaland.api.products.service.IProductService;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/giftbox")
public class GiftboxController {
    private final IGiftboxService iGiftboxService;
    private final IProductService iProductService;


    @PostMapping("/add")
    public Giftbox addGiftbox(@RequestBody RequestGiftbox requestGiftbox){
        return iGiftboxService.addGiftbox(requestGiftbox);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseGetUserGiftbox>> getAllbyUserId(@PathVariable Long userId){
        return ResponseEntity.ok(
                iGiftboxService.getAllbyUserId(userId)
        );
    }

}
