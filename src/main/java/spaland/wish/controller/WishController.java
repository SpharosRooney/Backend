package spaland.wish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.wish.model.Wish;
import spaland.wish.service.IWishService;
import spaland.wish.vo.RequestWish;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/wish")
public class WishController {
    private final IWishService iWishService;

    @PostMapping("/add")
    public Wish addwish(@RequestBody RequestWish requestWish){
        return iWishService.addWish(requestWish);
    }

    @GetMapping("/get/userId/{userId}")
    public List<Wish> getAllbyUserId(@PathVariable Long userId){
        return iWishService.getAllbyUserId(userId);
    }

}
