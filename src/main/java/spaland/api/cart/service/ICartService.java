package spaland.api.cart.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.cart.vo.RequestCart;
import spaland.api.cart.vo.RequestCartCount;
import spaland.api.cart.vo.RequestCheckCart;
import spaland.api.cart.vo.RequestDeleteCart;

import java.util.List;


public interface ICartService {
    ResponseEntity<Message> addCart(RequestCart requestCart, String userId);
    ResponseEntity<Message> modifyCart(RequestCartCount requestCartCount, String userId);
    ResponseEntity<Message> deleteProduct(String userId);
    ResponseEntity<Message> getAllByUserCart(String userId,Boolean isDelete);
    ResponseEntity<Message> checkboxCart(RequestCheckCart requestCheckCart, String userId, Boolean isDelete);
    ResponseEntity<Message> checkboxAllCart(String userId,Boolean isDelete);
    ResponseEntity<Message> deleteAllProduct(String userId,Boolean isDelete);

}
