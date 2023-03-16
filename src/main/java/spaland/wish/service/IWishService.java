package spaland.wish.service;

import spaland.products.model.Product;
import spaland.wish.model.Wish;
import spaland.wish.vo.RequestDeleteWish;
import spaland.wish.vo.RequestWish;
import spaland.wish.vo.ResponseGetUserWish;

import java.util.List;

public interface IWishService {
    Wish addWish(RequestWish requestWish);
    List<ResponseGetUserWish> getAllByUser(Long userId);
    List<ResponseGetUserWish> getAllByUserWish(Long userId, Boolean isDelete);

    void deleteWishList(RequestDeleteWish requestDeleteWish);
}
