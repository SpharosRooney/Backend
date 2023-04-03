package spaland.shipping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.exception.CustomException;
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

import static spaland.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements IUserShippingAddressService {

    private final IUserShippingAddressRepository iUserShippingAddressRepository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override

    public ResponseEntity<Message> addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress, String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()-> new CustomException(INVALID_ACCESS));

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
        Message message = new Message();
        message.setMessage("배송지가 추가되었습니다.");
        message.setData(null);

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @Override // TODO: 2023-03-31 이해가 잘 안됩니다!
    public ResponseEntity<Message> updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress, String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new CustomException(INVALID_ACCESS));
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
        Message message = new Message();
        message.setMessage("배송지가 추가되었습니다.");
        message.setData(null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public List<ResponseUserShippingAddress> getAllByUser(String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new CustomException(INVALID_ACCESS));
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserId(user.getId());
        if(userShippingAddressList.isEmpty()){
            throw new CustomException(INVALID_ACCESS);
        }
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
    public List<ResponseUserShippingAddress> getAllByIsUseByUser(String userId, Boolean isUse) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new CustomException(INVALID_ACCESS));
        List<UserShippingAddress> userShippingAddressList = iUserShippingAddressRepository.findAllByUserIdAndIsUse(user.getId(), isUse);
        List<ResponseUserShippingAddress> responseUserShippingAddresses = new ArrayList<>();

        for (int i = 0; i < userShippingAddressList.size(); i++) {
            responseUserShippingAddresses.add(modelMapper.map(userShippingAddressList.get(i), ResponseUserShippingAddress.class));
        }

        return responseUserShippingAddresses;

    }
}