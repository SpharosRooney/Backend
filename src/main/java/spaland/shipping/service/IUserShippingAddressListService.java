package spaland.shipping.service;

import spaland.shipping.model.UserShippingAddressList;
import spaland.shipping.vo.RequestUserShippingAddressList;

import java.util.List;

public interface IUserShippingAddressListService {
    UserShippingAddressList addUserShippingAddressList(RequestUserShippingAddressList requestUserShippingAddressList);
    //리스트 보이게 (void)


//    List<UserShippingAddressList> getByShippingAddressId(Long shippingAddressId);
//    유저 한명이 가지고 있는 배송지 목록을 반환해야 하기 때문에, 주석 처리합니다
    List<UserShippingAddressList> getAllbyUserId(Long userId);

}
