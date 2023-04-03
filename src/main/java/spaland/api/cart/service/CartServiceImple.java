package spaland.api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.cart.vo.RequestCart;
import spaland.api.cart.vo.RequestCartCount;
import spaland.api.cart.vo.RequestDeleteCart;
import spaland.api.cart.vo.ResponseGetUserCart;
import spaland.api.cart.model.Cart;
import spaland.api.cart.repository.ICartRepository;
import spaland.exception.CustomException;
import spaland.api.products.model.Product;
import spaland.api.products.repository.IProductRepository;
import spaland.api.users.model.User;
import spaland.api.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static spaland.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServiceImple implements ICartService {
    private final ICartRepository iCartRepository;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<Message> addCart(RequestCart requestCart, String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(() -> new CustomException(INVALID_ACCESS));
        Product product = iProductRepository.findById(requestCart.getProductId()).orElseThrow(() -> new CustomException(INVALID_PRODUCT));
        Optional<Cart> cartOptional = iCartRepository.findByUserIdAndIsDeleteAndProductId(user.getId(), Boolean.FALSE, requestCart.getProductId());

        Cart cart;
        if(cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setProductAmount(cart.getProductAmount() + requestCart.getProductAmount());
            cart = iCartRepository.save(cart);
        } else {
            cart = iCartRepository.save(Cart.builder()
                    .user(user)
                    .product(product)
                    .productAmount(requestCart.getProductAmount())
                    .isDelete(Boolean.FALSE)
                    .build()
            );
        }
        Message message = new Message();
        message.setMessage("장바구니에 추가되었습니다");
        message.setData(null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Message> getAllByUserCart(String userId, Boolean isDelete) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(() -> new CustomException(INVALID_ACCESS));
        List<Cart> carts = iCartRepository.findAllByUserIdAndIsDelete(user.getId(), isDelete);
        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
        for(int i = 0; i < carts.size(); i++){
            ResponseGetUserCart product = modelMapper.map(carts.get(i).getProduct(), ResponseGetUserCart.class);
            product.setProductAmount(carts.get(i).getProductAmount());
            responseGetUserCarts.add(product);
        }
        Message message = new Message();
        message.setMessage("success");
        message.setData(responseGetUserCarts);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Message> modifyCart(RequestCartCount requestCartCount, String userId) {
        Cart cart = iCartRepository.findById(requestCartCount.getId()).orElseThrow(() -> new CustomException(INVALID_MEMBER_CART));
        User user = iUserRepository.findByUserId(userId).orElseThrow(() -> new CustomException(INVALID_ACCESS));
        Optional<Cart> cartOptional = iCartRepository.findByIdAndIsDelete(cart.getId(), Boolean.FALSE);
        if (cartOptional.isPresent()){
            cart.setProductAmount(cart.getProductAmount() + requestCartCount.getProductAmount());
            iCartRepository.save(cart);
        }
        Message message = new Message();
        message.setMessage("수정되었습니다.");
        message.setData(null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> deleteProduct(RequestDeleteCart requestDeleteCart, String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(() -> new CustomException(INVALID_ACCESS));
        Cart cart = iCartRepository.findById(requestDeleteCart.getId()).orElseThrow(()-> new CustomException(INVALID_MEMBER_CART));
        cart.setIsDelete(true);
        iCartRepository.save(cart);

        Message message = new Message();
        message.setMessage("삭제되었습니다.");
        message.setData(null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}



