package spaland.shipping.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.shipping.model.UserShippingAddressList;
import spaland.shipping.repository.IShippingAddressRepository;
import spaland.shipping.repository.IUserShippingAddressListRepository;
import spaland.shipping.vo.RequestUserShippingAddressList;
import spaland.users.repository.IUserRespository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserShippingAddressListServiceImple implements IUserShippingAddressListService{

    private final IShippingAddressRepository iShippingAddressRepository;
    private final IUserShippingAddressListRepository iUserShippingAddressListRepository;
    private final IUserRespository iUserRespository;



    @Override
    public UserShippingAddressList addUserShippingAddressList(RequestUserShippingAddressList requestUserShippingAddressList) {

        UserShippingAddressList userShippingAddressList = iUserShippingAddressListRepository.save(UserShippingAddressList.builder()
                        .user(iUserRespository.findById(requestUserShippingAddressList.getUserId()).get())
                        .shippingAddress(iShippingAddressRepository.findById(requestUserShippingAddressList.getShippingAddressId()).get())
            .build());

        log.info("{}", userShippingAddressList.toString());

        return  userShippingAddressList; //리스트 보이게

    }

//    @Override
//    public List<UserShippingAddressList> getByShippingAddressId(Long shippingAddressId) {
//        return iUserShippingAddressListRepository.findAllByShippingAddressId(shippingAddressId);
//    }

    @Override
    public List<UserShippingAddressList> getAllbyUserId(Long userId) {
        return iUserShippingAddressListRepository.findAllByUserId(userId);
    }



}
