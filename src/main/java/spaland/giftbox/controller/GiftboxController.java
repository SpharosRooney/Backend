package spaland.giftbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.giftbox.model.Giftbox;
import spaland.giftbox.service.IGiftboxService;
import spaland.giftbox.vo.RequestGiftbox;
import spaland.giftbox.vo.ResponseGetUserGiftbox;
import spaland.products.service.IProductService;
import spaland.products.vo.ResponseProduct;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/giftbox")
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
