package spaland.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.cart.model.Cart;
import spaland.cart.repository.ICartRepository;
import spaland.cart.vo.RequestCart;
import spaland.cart.vo.RequestCartCount;
import spaland.cart.vo.RequestDeleteCart;
import spaland.cart.vo.ResponseGetUserCart;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.users.model.User;
import spaland.users.repository.IUserRespository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServiceImple implements ICartService{
    private final ICartRepository iCartRepository;
    private final IProductRepository iProductRepository;
    private final IUserRespository iUserRespository;

    @Override
    public Cart addCart(RequestCart requestCart) {
        Cart cart = iCartRepository.save(Cart.builder()
                        .user(iUserRespository.findById(requestCart.getUserId()).get())
                        .product(iProductRepository.findById(requestCart.getProductId()).get())
                        .productAmount(requestCart.getProductAmount())
                .build()
        );
        log.info("{}", cart.toString());

        return  cart; //리스트 보이게

    }

    @Override
    public Product getByProductId(Long productId) {
        return iCartRepository.findAllByProductId(productId);
    }


    @Override
    public List<ResponseGetUserCart> getAllByUser(Long userId) {
        List<Cart> cart = iCartRepository.findAllByUserId(userId);
        List<ResponseGetUserCart> responseGetUserCarts = new ArrayList<>();
        cart.forEach(
                userCart -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseGetUserCarts.add(
                            modelMapper.map(userCart, ResponseGetUserCart.class)
                    );
                }

        );
        return responseGetUserCarts;
    } //유저의 장바구니 목록을 보여줌


    @Override
    public void modifyCart(RequestCartCount requestCartCount) {
        Cart cart = iCartRepository.findById(requestCartCount.getId()).get();
        cart.setProductAmount(cart.getProductAmount() + requestCartCount.getProductAmount());
        iCartRepository.save(cart);
    }

    @Override
    public void deleteProduct(RequestDeleteCart requestDeleteCart) {

//        List<Cart> carts = requestDeleteCart.getCartId();
//
//        for(int i = 0; i<carts.size();i++) {
//            Long id = carts.get(i).getId();
//            Cart cart = iCartRepository.findById(id).get();
//            cart.setDelete(false);
//            iCartRepository.save(cart);
        Cart cart = iCartRepository.findById(requestDeleteCart.getId()).get();
        cart.setDelete(true); //삭제됨(1)
        iCartRepository.save(cart);

        }

    }



