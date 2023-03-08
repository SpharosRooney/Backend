package spaland.shipping.service;

import spaland.shipping.vo.RequestAddUserShippingAddress;
import spaland.shipping.vo.RequestEditUserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;


import java.util.List;

public interface IUserShippingAddressService {

    void addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress);
    void updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress);
    ResponseUserShippingAddress getShippingAddress(Long userShippingId);
    List<ResponseUserShippingAddress> getAllByUser(Long userId);
    List<ResponseUserShippingAddress> getAllByIsUseByUser(Long userId, Boolean isUse);

}
