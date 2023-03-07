package spaland.giftbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.giftbox.model.Giftbox;
import spaland.giftbox.service.IGiftboxService;
import spaland.giftbox.vo.RequestGiftbox;
import spaland.products.model.Product;
import spaland.products.service.IProductService;
import spaland.users.service.IUserService;

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

    @GetMapping("/get/userId/{userId}")
    public List<Giftbox> getAllbyUserId(@PathVariable Long userId){
        return iGiftboxService.getAllbyUserId(userId);
    }

    @GetMapping("/get/productId/{productId}")
    public Product getByProductId(@PathVariable Long productId){
        return iProductService.getProduct(productId);
    }

}
