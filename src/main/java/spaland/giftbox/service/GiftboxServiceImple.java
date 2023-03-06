package spaland.giftbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.giftbox.model.Giftbox;
import spaland.giftbox.repository.IGiftboxRepository;
import spaland.giftbox.vo.RequestGiftbox;
import spaland.products.repository.IProductRepository;
import spaland.users.repository.IUserRespository;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class GiftboxServiceImple implements IGiftboxService{
    private final IGiftboxRepository iGiftboxRepository;
    private final IUserRespository iUserRespository;
    private final IProductRepository iProductRepository;


    @Override
    public Giftbox addGiftbox(RequestGiftbox requestGiftbox) {
        Giftbox giftbox = iGiftboxRepository.save(Giftbox.builder()
                .user(iUserRespository.findById(requestGiftbox.getUserId()).get())
                .product(iProductRepository.findById(requestGiftbox.getProductId()).get())
                .build()
        );

        log.info("{}", giftbox.toString());

        return  giftbox; //리스트 보이게

    }


//    @Override
//    public List<Giftbox> getByProductId(Long productId) {
//        return iGiftboxRepository.findAllByProductId(productId);
//    }

    @Override
    public List<Giftbox> getAllbyUserId(Long userId) {
        return iGiftboxRepository.findAllByUserId(userId);
    }
}
