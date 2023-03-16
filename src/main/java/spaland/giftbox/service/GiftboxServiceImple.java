package spaland.giftbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.giftbox.model.Giftbox;
import spaland.giftbox.repository.IGiftboxRepository;
import spaland.giftbox.vo.RequestGiftbox;
import spaland.giftbox.vo.ResponseGetUserGiftbox;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;
import spaland.users.repository.IUserRespository;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class GiftboxServiceImple implements IGiftboxService{
    private final IGiftboxRepository iGiftboxRepository;
    private final IUserRespository iUserRespository;
    private final IProductRepository iProductRepository;
    private final IGiftboxRepository iUserShippingAddressRepository;

    @Override
    public Giftbox addGiftbox(RequestGiftbox requestGiftbox) {
        Giftbox giftbox = iGiftboxRepository.save(Giftbox.builder()
                .user(iUserRespository.findById(requestGiftbox.getUserId()).get())
                .product(iProductRepository.findById(requestGiftbox.getProductId()).get())
                .sender(requestGiftbox.getSender())
                .state(requestGiftbox.getState())
                .letter(requestGiftbox.getLetter())
                .giftAmount(requestGiftbox.getGiftAmount())
                .build()
        );

        log.info("{}", giftbox.toString());

        return  giftbox; //리스트 보이게
    }


    @Override
    public List<ResponseGetUserGiftbox> getAllbyUserId(Long userId) {
        List<Giftbox> giftboxes = iGiftboxRepository.findAllByUserId(userId);
        List<ResponseGetUserGiftbox> responseGetUserGiftboxes = new ArrayList<>();
        giftboxes.forEach(
                userGiftbox -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseGetUserGiftboxes.add(
                            modelMapper.map(userGiftbox, ResponseGetUserGiftbox.class)
                    );
                }
        );
        return responseGetUserGiftboxes;
    }


}
