package spaland.zincomplete.wish.service;

import spaland.zincomplete.wish.vo.RequestWish;
import spaland.zincomplete.wish.vo.ResponseGetUserWish;
import spaland.zincomplete.wish.model.Wish;
import spaland.zincomplete.wish.vo.RequestDeleteWish;

import java.util.List;

public interface IWishService {
    Wish addWish(RequestWish requestWish);
    List<ResponseGetUserWish> getAllByUser(Long userId);
    List<ResponseGetUserWish> getAllByUserWish(Long userId, Boolean isDelete);

    void deleteWishList(RequestDeleteWish requestDeleteWish);
}
