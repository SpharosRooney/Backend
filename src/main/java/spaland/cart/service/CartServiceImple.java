package spaland.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.cart.model.Cart;
import spaland.cart.repository.ICartRepository;
import spaland.cart.vo.RequestCart;
import spaland.cart.vo.RequestCartCount;
import spaland.cart.vo.RequestDeleteCart;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.users.repository.IUserRespository;

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
    public List<Cart> getByUserId(Long userId) {
        return iCartRepository.findAllByUserId(userId);
    }


    @Override
    public void modifyCart(RequestCartCount requestCartCount) {
        Cart cart = iCartRepository.findById(requestCartCount.getId()).get();
        cart.setProductAmount(cart.getProductAmount() + requestCartCount.getProductAmount());
        iCartRepository.save(cart);
    }

    @Override
    public void deleteProduct(RequestDeleteCart requestDeleteCart) {

        List<Cart> carts = requestDeleteCart.getCartId();

        for(int i = 0; i<carts.size();i++) {
            Long id = carts.get(i).getId();
            Cart cart = iCartRepository.findById(id).get();
            cart.setDelete(false); //삭제되는거는 0(False), 장바구니에 남아 있는 것은 1(True)
            iCartRepository.save(cart);

        }

    }


}
