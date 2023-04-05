package spaland.api.cart.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.cart.vo.RequestCart;
import spaland.api.cart.vo.RequestCartCount;
import spaland.api.cart.vo.RequestCheckCart;
import spaland.api.cart.vo.RequestDeleteCart;


public interface ICartService {
    ResponseEntity<Message> addCart(RequestCart requestCart, String userId);
    ResponseEntity<Message> modifyCart(RequestCartCount requestCartCount, String userId);
    ResponseEntity<Message> deleteProduct(RequestDeleteCart requestDeleteCart, String userId);
    ResponseEntity<Message> getAllByUserCart(String userId,Boolean isDelete); //유저의 카트를 보는것
    ResponseEntity<Message> checkboxCart(RequestCheckCart requestCheckCart, String userId);

}
