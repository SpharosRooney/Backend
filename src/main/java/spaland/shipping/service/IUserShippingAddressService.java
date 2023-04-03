package spaland.shipping.service;

import spaland.shipping.vo.RequestAddUserShippingAddress;
import spaland.shipping.vo.RequestEditUserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;


import java.util.List;

public interface IUserShippingAddressService {

    void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress,String userId);
    void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress,String userId);
//    ResponseUserShippingAddress getShippingAddress(Long userShippingId);
    List<ResponseUserShippingAddress> getAllByUser(String userId);
    List<ResponseUserShippingAddress> getAllByIsUseByUser(String userId, Boolean isUse);

}
