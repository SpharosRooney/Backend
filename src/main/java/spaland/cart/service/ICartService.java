package spaland.cart.service;

import spaland.cart.model.Cart;
import spaland.cart.vo.*;
import spaland.giftCard.vo.ResponseGiftCard;
import spaland.products.model.Product;

import java.util.List;

public interface ICartService {
    Cart addCart(RequestCart requestCart,String userId);
    void modifyCart(RequestCartCount requestCartCount,String userId);
    void deleteProduct(RequestDeleteCart requestDeleteCart,String userId);

//    List<ResponseGetUserCart> getAllByUser(Long userId);
    List<ResponseGetUserCart> getAllByUserCart(String userId,Boolean isDelete); //유저의 카트를 보는것

}
