package spaland.cart.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.cart.model.Cart;
import spaland.cart.vo.*;
import spaland.giftCard.vo.ResponseGiftCard;
import spaland.products.model.Product;

import java.util.List;

public interface ICartService {
    ResponseEntity<Message> addCart(RequestCart requestCart, String userId);
    ResponseEntity<Message> modifyCart(RequestCartCount requestCartCount,String userId);
    ResponseEntity<Message> deleteProduct(RequestDeleteCart requestDeleteCart,String userId);
    ResponseEntity<Message> getAllByUserCart(String userId,Boolean isDelete); //유저의 카트를 보는것

}
