package spaland.zincomplete.wish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.zincomplete.wish.service.IWishService;
import spaland.zincomplete.wish.vo.RequestDeleteWish;
import spaland.zincomplete.wish.vo.RequestWish;
import spaland.zincomplete.wish.vo.ResponseGetUserWish;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/wish")
public class WishController {
    private final IWishService iWishService;

    @PostMapping("/add")
    public void addwish(@RequestBody RequestWish requestWish){
       iWishService.addWish(requestWish);
    }

    @GetMapping("/isDelete")
    public ResponseEntity<List<ResponseGetUserWish>> getAllByUserIdAndIsDelete(@RequestParam Long userId, Boolean isDelete){
        return ResponseEntity.ok(
                iWishService.getAllByUserWish(userId, isDelete)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseGetUserWish>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(
                iWishService.getAllByUser(userId)
        );
    }

    @PutMapping("/delete") //위시리스트 삭제
    public void deleteWishList(@RequestBody RequestDeleteWish requestDeleteWish){
        iWishService.deleteWishList(requestDeleteWish);
    }
}
