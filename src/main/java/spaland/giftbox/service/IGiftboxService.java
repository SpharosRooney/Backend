package spaland.giftbox.service;

import spaland.giftbox.model.Giftbox;
import spaland.giftbox.vo.RequestGiftbox;
import spaland.giftbox.vo.ResponseGetUserGiftbox;
import spaland.products.model.Product;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;
import spaland.users.model.User;

import java.util.List;

public interface IGiftboxService {
    Giftbox addGiftbox(RequestGiftbox requestGiftbox);
    //리스트 보이게한거, 안보이는건 (void)
    Product getByProductId(Long productId);
    List<ResponseGetUserGiftbox> getAllbyUserId(Long userId);



}
