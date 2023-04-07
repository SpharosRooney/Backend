package spaland.api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.cart.vo.RequestCart;
import spaland.api.cart.vo.RequestCartCount;
import spaland.api.cart.vo.RequestCheckCart;
import spaland.api.cart.vo.RequestDeleteCart;
import spaland.api.cart.service.ICartService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService iCartService;
    @PostMapping()
    public ResponseEntity<Message> addCart(Authentication authentication, @RequestBody RequestCart requestCart){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.addCart(requestCart, userDetails.getUsername());
    }


    @GetMapping()
    public ResponseEntity<Message> getAllByUserCart(Authentication authentication, @RequestParam(value = "isDelete") Boolean isDelete){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.getAllByUserCart(userDetails.getUsername(), Boolean.FALSE);
    }

    @PutMapping("/modify")
    public ResponseEntity<Message> modifyCart(Authentication authentication,@RequestBody RequestCartCount requestCartCount){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.modifyCart(requestCartCount, userDetails.getUsername());
    }

    @PutMapping()
    public ResponseEntity<Message> deleteProduct(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.deleteProduct(userDetails.getUsername());
    }

    @PatchMapping("/check")
    public ResponseEntity<Message> checkboxCart(Authentication authentication, @RequestBody RequestCheckCart requestCheckCart){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.checkboxCart(requestCheckCart,userDetails.getUsername(),false);
    }

    @PatchMapping("/check/all")
    public ResponseEntity<Message> checkboxAllCart(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.checkboxAllCart(userDetails.getUsername(),false);
    }

    @PatchMapping("/deleteAll")
    public ResponseEntity<Message> deleteAllProduct(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.deleteAllProduct(userDetails.getUsername(),false);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Message> deleteCart(Authentication authentication, @RequestBody RequestDeleteCart requestDeleteCart){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return iCartService.deleteCart(userDetails.getUsername(),requestDeleteCart.getId());
    }


}
