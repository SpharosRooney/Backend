package spaland.wish.service;

import spaland.wish.model.Wish;
import spaland.wish.vo.RequestDeleteWish;
import spaland.wish.vo.RequestWish;

import java.util.List;

public interface IWishService {
    Wish addWish(RequestWish requestWish);

//    List<Wish> getByProductId(Long productId);
    List<Wish> getAllbyUserId(Long userId);

    void deleteWishList(RequestDeleteWish requestDeleteWish);
}
