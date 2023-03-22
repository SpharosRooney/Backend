package spaland.wish.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.users.repository.IUserRepository;
import spaland.wish.model.Wish;
import spaland.wish.repository.IWishRepository;
import spaland.wish.vo.RequestDeleteWish;
import spaland.wish.vo.RequestWish;
import spaland.wish.vo.ResponseGetUserWish;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class WishServiceImple implements IWishService{
    private final IWishRepository iWishRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    @Override
    public Wish addWish(RequestWish requestWish) {
        Wish wish = iWishRepository.save(Wish.builder()
                .user(iUserRepository.findById(requestWish.getUserId()).get())
                .product(iProductRepository.findById(requestWish.getProductId()).get())
                .build());

        log.info("{}", wish.toString());
        return wish;
    }

    @Override
    public Product getByProductId(Long productId) {
        return iWishRepository.findByProductId(productId);
    }




//    @Override
//    public List<Wish> getAllbyUserId(Long userId) {
//        return iWishRepository.findAllByUserId(userId);
//    }

    @Override
    public List<ResponseGetUserWish> getAllByUser(Long userId) {
        List<Wish> wishes = iWishRepository.findAllByUserId(userId);
        List<ResponseGetUserWish> responseGetUserWishes = new ArrayList<>();
        wishes.forEach(
                userWish -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseGetUserWishes.add(
                            modelMapper.map(userWish, ResponseGetUserWish.class)
                    );
                }
        );
        return responseGetUserWishes;
    }

    @Override
    public List<ResponseGetUserWish> getAllByUserWish(Long userId, Boolean isDelete) {
        List<Wish> wishes = iWishRepository.findAllByUserIdAndIsDelete(userId,isDelete);
        List<ResponseGetUserWish> responseGetUserWishes = new ArrayList<>();
        wishes.forEach(
                userWish -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseGetUserWishes.add(
                            modelMapper.map(userWish, ResponseGetUserWish.class)
                    );
                }
        );
        return responseGetUserWishes;
    }


    @Override
    public void deleteWishList(RequestDeleteWish requestDeleteWish) {
        Wish wish = iWishRepository.findById(requestDeleteWish.getId()).get();
        wish.setIsDelete(true);
        iWishRepository.save(wish);

    }
}
