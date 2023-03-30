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
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements IUserShippingAddressService {

    private final IUserShippingAddressRepository iUserShippingAddressRepository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();

//    @Override
//    public void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress) {
//
//        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(requestAddUserShippingAddress.getUserId());
//
//        if (userShippingAddressList.size() == 0) {
//            requestAddUserShippingAddress.setIsUse(true); // 기존 배송지 없으면 지금 입력하는 배송지를 기본 배송지로 해주는데
//        } else {
//            if (requestAddUserShippingAddress.getIsUse().equals(true)) { // 입력하는 배송지값이 1이면
//                for (UserShippingAddress userShippingAddress : userShippingAddressList) { // 나머지 = false
//                    userShippingAddress.setIsUse(false);
//                    iUserShippingAddressRepository.save(userShippingAddress);
//                }
//            }
//        }
//
//        iUserShippingAddressRepository.save(
//                UserShippingAddress.builder()
//                        .user(iUserRepository.findById(requestAddUserShippingAddress.getUserId()).get())
//                        .address(requestAddUserShippingAddress.getAddress())
//                        .detailAddress(requestAddUserShippingAddress.getDetailAddress())
//                        .shippingPhone(requestAddUserShippingAddress.getShippingPhone())
//                        .zipCode(requestAddUserShippingAddress.getZipCode())
//                        .isUse(requestAddUserShippingAddress.getIsUse())
//                        .build()
//        );
////    }
//
//    @Override
//    public void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress) {
//
//        UserShippingAddress userShippingAddress = iUserShippingAddressRepository.findById(requestEditUserShippingAddress.getId()).get();
//
//        if (requestEditUserShippingAddress.getIsUse() == true) {
//            List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(userShippingAddress.getUser().getId());
//            for (UserShippingAddress iter : userShippingAddressList) { // 나머지 = false
//                iter.setIsUse(false);
//                iUserShippingAddressRepository.save(iter);
//            }
//        }
//
//        if (requestEditUserShippingAddress.getZipCode() != null)
//            userShippingAddress.setZipCode(requestEditUserShippingAddress.getZipCode());
//        if (requestEditUserShippingAddress.getAddress() != null)
//            userShippingAddress.setAddress(requestEditUserShippingAddress.getAddress());
//        if (requestEditUserShippingAddress.getDetailAddress() != null)
//            userShippingAddress.setDetailAddress(requestEditUserShippingAddress.getDetailAddress());
//        if (requestEditUserShippingAddress.getIsUse() != null)
//            userShippingAddress.setIsUse(requestEditUserShippingAddress.getIsUse());
//        if (requestEditUserShippingAddress.getShippingPhone() != null)
//            userShippingAddress.setShippingPhone(requestEditUserShippingAddress.getShippingPhone());
//
//        iUserShippingAddressRepository.save(userShippingAddress);
//    }

//    @Override
//    public ResponseUserShippingAddress getShippingAddress(Long userShippingId) {
//        UserShippingAddress userShippingAddress = iUserShippingAddressRepository.findById(userShippingId).get();
//
//        return modelMapper.map(userShippingAddress, ResponseUserShippingAddress.class);
//    }
//
//    @Override
//    public List<ResponseUserShippingAddress> getAllByUser(Long userId) {
//
//        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(userId);
//        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();
//
//        userShippingAddressList.forEach(
//                userShippingAddress -> {
//                    responseUserShippingAddresses.add(
//                            modelMapper.map(userShippingAddress, ResponseUserShippingAddress.class)
//                    );
//                }
//        );
//
//        return responseUserShippingAddresses;
//    }

//    @Override
//    public List<ResponseUserShippingAddress> getAllByIsUseByUser(Long userId, Boolean isUse) {
//
//        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserIdAndIsUse(userId, isUse);
//        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();
//
//        for (int i = 0; i < userShippingAddressList.size(); i++) {
//            responseUserShippingAddresses.add(modelMapper.map(userShippingAddressList.get(i), ResponseUserShippingAddress.class));
//        }
//
//        return responseUserShippingAddresses;
//    }

    @Override
    public void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress, String userEmail) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()-> new RuntimeException());

        List<UserShippingAddress> userShippingAddressList =
                iUserShippingAddressRepository.findAllByUserId(user.getId());

        if (userShippingAddressList.size() == 0) {
            requestAddUserShippingAddress.setIsUse(true); // 기존 배송지 없으면 지금 입력하는 배송지를 기본 배송지로 해주는데
        } else {
            if (requestAddUserShippingAddress.getIsUse().equals(true)) { // 입력하는 배송지값이 1이면
                for (UserShippingAddress userShippingAddress : userShippingAddressList) { // 나머지 = false
                    userShippingAddress.setIsUse(false);
                    iUserShippingAddressRepository.save(userShippingAddress);
                }
            }
        }

        iUserShippingAddressRepository.save(
                UserShippingAddress.builder()
                        .user(iUserRepository.findById(user.getId()).get())
                        .address(requestAddUserShippingAddress.getAddress())
                        .detailAddress(requestAddUserShippingAddress.getDetailAddress())
                        .shippingPhone(requestAddUserShippingAddress.getShippingPhone())
                        .zipCode(requestAddUserShippingAddress.getZipCode())
                        .isUse(requestAddUserShippingAddress.getIsUse())
                        .build()
        );
    }

    @Override
    public void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress, String userEmail) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()->new RuntimeException());
        UserShippingAddress userShippingAddress = iUserShippingAddressRepository.findById(user.getId()).get();

        if (requestEditUserShippingAddress.getIsUse() == true) {
            List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(userShippingAddress.getUser().getId());
            for (UserShippingAddress iter : userShippingAddressList) { // 나머지 = false
                iter.setIsUse(false);
                iUserShippingAddressRepository.save(iter);
            }
        }

        if (requestEditUserShippingAddress.getZipCode() != null)
            userShippingAddress.setZipCode(requestEditUserShippingAddress.getZipCode());
        if (requestEditUserShippingAddress.getAddress() != null)
            userShippingAddress.setAddress(requestEditUserShippingAddress.getAddress());
        if (requestEditUserShippingAddress.getDetailAddress() != null)
            userShippingAddress.setDetailAddress(requestEditUserShippingAddress.getDetailAddress());
        if (requestEditUserShippingAddress.getIsUse() != null)
            userShippingAddress.setIsUse(requestEditUserShippingAddress.getIsUse());
        if (requestEditUserShippingAddress.getShippingPhone() != null)
            userShippingAddress.setShippingPhone(requestEditUserShippingAddress.getShippingPhone());

        iUserShippingAddressRepository.save(userShippingAddress);
    }

    @Override
    public List<ResponseUserShippingAddress> getAllByUser(String userEmail) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()->new RuntimeException());
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(user.getId());
        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();

        userShippingAddressList.forEach(
                userShippingAddress -> {
                    responseUserShippingAddresses.add(
                            modelMapper.map(userShippingAddress, ResponseUserShippingAddress.class)
                    );
                }
        );

        return responseUserShippingAddresses;
    }

    @Override
    public List<ResponseUserShippingAddress> getAllByIsUseByUser(String userEmail, Boolean isUse) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()->new RuntimeException());
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserIdAndIsUse(user.getId(), isUse);
        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();

        for (int i = 0; i < userShippingAddressList.size(); i++) {
            responseUserShippingAddresses.add(modelMapper.map(userShippingAddressList.get(i), ResponseUserShippingAddress.class));
        }

        return responseUserShippingAddresses;

    }
}