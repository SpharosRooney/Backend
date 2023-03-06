package spaland.giftbox.service;

import spaland.giftbox.model.Giftbox;
import spaland.giftbox.vo.RequestGiftbox;

import java.util.List;

public interface IGiftboxService {
    Giftbox addGiftbox(RequestGiftbox requestGiftbox);
    //리스트 보이게한거, 안보이는건 (void)

//    List<Giftbox> getByProductId(Long productId);
    List<Giftbox> getAllbyUserId(Long userId);

}
