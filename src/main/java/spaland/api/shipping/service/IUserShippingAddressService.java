package spaland.api.shipping.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.shipping.vo.RequestAddUserShippingAddress;
import spaland.api.shipping.vo.RequestEditUserShippingAddress;

public interface IUserShippingAddressService {

    ResponseEntity<Message> addShippingAddressByUser(RequestAddUserShippingAddress requestAddUserShippingAddress, String userId);
    ResponseEntity<Message> updateShippingAddressByUser(RequestEditUserShippingAddress requestEditUserShippingAddress,String userId);
//    ResponseUserShippingAddress getShippingAddress(Long userShippingId);
    ResponseEntity<Message> getAllByUser(String userId);
    ResponseEntity<Message> getAllByIsUseByUser(String userId, Boolean isUse);

}
