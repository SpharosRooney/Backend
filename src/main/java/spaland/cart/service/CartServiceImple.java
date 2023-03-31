package spaland.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.cart.model.Cart;
import spaland.cart.repository.ICartRepository;
import spaland.cart.vo.*;
import spaland.giftCard.vo.ResponseGiftCard;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServiceImple implements ICartService {
    private final ICartRepository iCartRepository;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Cart addCart(RequestCart requestCart,String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException());
        Product product = iProductRepository.findById(requestCart.getProductId()).orElseThrow(() -> new RuntimeException());
        Optional<Cart> cartOptional = iCartRepository.findByUserIdAndIsDeleteAndProductId(user.getId(), Boolean.FALSE, requestCart.getProductId());

        Cart cart;
        if(cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setProductAmount(cart.getProductAmount() + requestCart.getProductAmount());
        } else {
            cart = iCartRepository.save(Cart.builder()
                    .user(user)
                    .product(product)
                    .productAmount(requestCart.getProductAmount())
                    .isDelete(Boolean.FALSE)
                    .build()
            );
        }
        return iCartRepository.save(cart);
    }


    @Override
    public List<ResponseGetUserCart> getAllByUserCart(String userId, Boolean isDelete) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new RuntimeException());
        List<Cart> carts = iCartRepository.findAllByUserIdAndIsDelete(user.getId(), isDelete);
        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
        for(int i = 0; i < carts.size(); i++){
            ResponseGetUserCart product = modelMapper.map(carts.get(i).getProduct(), ResponseGetUserCart.class);
            product.setProductAmount(carts.get(i).getProductAmount());
            responseGetUserCarts.add(product);
        }
        return responseGetUserCarts;
    }


    @Override
    public void modifyCart(RequestCartCount requestCartCount,String userId) {
        Cart cart = iCartRepository.findById(requestCartCount.getId()).get();
        Optional<Cart> cartOptional = iCartRepository.findByIdAndIsDelete(cart.getId(), Boolean.FALSE);
        if (cartOptional.isPresent()){
            cart.setProductAmount(cart.getProductAmount() + requestCartCount.getProductAmount());
            iCartRepository.save(cart);
        }
    }

    @Override
    public void deleteProduct(RequestDeleteCart requestDeleteCart,String userId) {
        Cart cart = iCartRepository.findById(requestDeleteCart.getId()).get();
        cart.setIsDelete(true);
        iCartRepository.save(cart);
    }

//    @Override
//    public List<ResponseGetUserCart> getAllByUser(Long userId) {
//        List<Cart> cart = iCartRepository.findAllByUserId(userId);
//        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
//        cart.forEach(
//                userCart -> {
//                    responseGetUserCarts.add(
//                            modelMapper.map(userCart, ResponseGetUserCart.class)
//                    );
//                }
//        );
//        return responseGetUserCarts;
//    }


}



