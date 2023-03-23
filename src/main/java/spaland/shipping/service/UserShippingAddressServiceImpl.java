package spaland.shipping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.repository.IUserShippingAddressRepository;
import spaland.shipping.vo.RequestAddUserShippingAddress;
import spaland.shipping.vo.RequestEditUserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements IUserShippingAddressService {

    private final IUserShippingAddressRepository iUserShippingAddressRepository;
    private final IUserRepository iUserRepository;

    @Override
    public void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress) {


            iUserShippingAddressRepository.save(
                    UserShippingAddress.builder()
                            .user(iUserRepository.findById(requestAddUserShippingAddress.getUserId()).get())
                            .address(requestAddUserShippingAddress.getAddress())
                            .detailAddress(requestAddUserShippingAddress.getDetailAddress())
                            .shippingPhone(requestAddUserShippingAddress.getShippingPhone())
                            .zipCode(requestAddUserShippingAddress.getZipCode())
                            .isUse(requestAddUserShippingAddress.getIsUse())
                            .build()
            );

    }

    @Override
    public void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress) {

        UserShippingAddress userShippingAddress = iUserShippingAddressRepository.findById(requestEditUserShippingAddress.getId()).get();

        ModelMapper modelMapper = new ModelMapper();
        userShippingAddress = modelMapper.map(requestEditUserShippingAddress, UserShippingAddress.class);
        iUserShippingAddressRepository.save(userShippingAddress);

    }

    @Override
    public ResponseUserShippingAddress getShippingAddress(Long userShippingId) {
        UserShippingAddress userShippingAddress = iUserShippingAddressRepository.findById(userShippingId).get();
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map( userShippingAddress, ResponseUserShippingAddress.class);
    }

    @Override
    public List<ResponseUserShippingAddress> getAllByUser(Long userId) {
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(userId);

        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();

        userShippingAddressList.forEach(
                userShippingAddress -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseUserShippingAddresses.add(
                            modelMapper.map(userShippingAddress, ResponseUserShippingAddress.class)
                    );
                }
        );

        return responseUserShippingAddresses;
    }

    @Override
    public List<ResponseUserShippingAddress> getAllByIsUseByUser(Long userId, Boolean isUse) {
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserIdAndIsUse(userId, isUse);
        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
//        return userShippingAddressList.stream().map(t -> modelMapper.map(t, ResponseUserShippingAddress.class)).collect(Collectors.toList());

        userShippingAddressList.forEach(
                userShippingAddress -> {

                    responseUserShippingAddresses.add(
                            modelMapper.map(userShippingAddress, ResponseUserShippingAddress.class)
                    );
                }
        );

        for (int i = 0; i < userShippingAddressList.size(); i++) {
            responseUserShippingAddresses.add(modelMapper.map(userShippingAddressList.get(i),ResponseUserShippingAddress.class));
        }

        return responseUserShippingAddresses;
    }
}