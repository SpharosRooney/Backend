package spaland.wish.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.products.repository.IProductRepository;
import spaland.users.repository.IUserRespository;
import spaland.wish.model.Wish;
import spaland.wish.repository.IWishRepository;
import spaland.wish.vo.RequestWish;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class WishServiceImple implements IWishService{
    private final IWishRepository iWishRepository;
    private final IUserRespository iUserRespository;
    private final IProductRepository iProductRepository;
    @Override
    public Wish addWish(RequestWish requestWish) {
        Wish wish = iWishRepository.save(Wish.builder()
                .user(iUserRespository.findById(requestWish.getUserId()).get())
                .product(iProductRepository.findById(requestWish.getProductId()).get())
                .build());

        log.info("{}", wish.toString());
        return wish;
    }

//    @Override
//    public List<Wish> getByProductId(Long productId) {
//        return iWishRepository.findAllByProduct(productId);
//    }


    @Override
    public List<Wish> getAllbyUserId(Long userId) {
        return iWishRepository.findAllByUserId(userId);
    }
}
