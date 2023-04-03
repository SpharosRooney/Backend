package spaland.zincomplete.giftbox.service;

import spaland.zincomplete.giftbox.model.Giftbox;
import spaland.zincomplete.giftbox.vo.RequestGiftbox;
import spaland.zincomplete.giftbox.vo.ResponseGetUserGiftbox;

import java.util.List;

public interface IGiftboxService {
    Giftbox addGiftbox(RequestGiftbox requestGiftbox);
    //리스트 보이게한거, 안보이는건 (void)
    List<ResponseGetUserGiftbox> getAllbyUserId(Long userId);



}
