package spaland.shipping.service;

import spaland.shipping.vo.RequestAddUserShippingAddress;
import spaland.shipping.vo.RequestEditUserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;


import java.util.List;

public interface IUserShippingAddressService {

    void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress,String userEmail);
    void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress,String userEmail);
//    ResponseUserShippingAddress getShippingAddress(Long userShippingId);
    List<ResponseUserShippingAddress> getAllByUser(String userEmail);
    List<ResponseUserShippingAddress> getAllByIsUseByUser(String userEmail, Boolean isUse);

}
