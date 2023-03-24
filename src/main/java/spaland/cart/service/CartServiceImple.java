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
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServiceImple implements ICartService{
    private final ICartRepository iCartRepository;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Cart addCart(RequestCart requestCart) {
        Cart cart = iCartRepository.save(Cart.builder()
                        .user(iUserRepository.findById(requestCart.getUserId()).get())
                        .product(iProductRepository.findById(requestCart.getProductId()).get())
                        .productAmount(requestCart.getProductAmount())
                .build()
        );
        cart.setIsDelete(false);
        iCartRepository.save(cart);
        log.info("{}", cart.toString());
        return cart; //리스트 보이게
    }


    @Override
    public List<ResponseGetUserCart> getAllByUser(Long userId) {
        List<Cart> cart = iCartRepository.findAllByUserId(userId);
        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
        cart.forEach(
                userCart -> {
                    responseGetUserCarts.add(
                            modelMapper.map(userCart, ResponseGetUserCart.class)
                    );
                }
        );
        return responseGetUserCarts;
    }

    @Override
    public List<ResponseGetUserCart> getAllByUserCart(Long userId, Boolean isDelete) {
        List<Cart> carts = iCartRepository.findAllByUserIdAndIsDelete(userId, isDelete);
        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
        for(int i = 0; i < carts.size(); i++){
            ResponseGetUserCart product = modelMapper.map(carts.get(i).getProduct(), ResponseGetUserCart.class);
            product.setProductAmount(carts.get(i).getProductAmount());
            responseGetUserCarts.add(product);
        }
        return responseGetUserCarts;
    }


    @Override
    public void modifyCart(RequestCartCount requestCartCount) {
        Cart cart = iCartRepository.findById(requestCartCount.getId()).get();
        cart.setProductAmount(cart.getProductAmount() + requestCartCount.getProductAmount());
        iCartRepository.save(cart);
    }

    @Override
    public void deleteProduct(RequestDeleteCart requestDeleteCart) {
        Cart cart = iCartRepository.findById(requestDeleteCart.getId()).get();
        cart.setIsDelete(true);
        iCartRepository.save(cart);
    }




}



