package spaland.giftCard.service;


import spaland.giftCard.vo.RequestChargeUserGiftCard;
import spaland.giftCard.vo.RequestUserGiftCard;
import spaland.giftCard.model.GiftCard;

import java.util.List;

public interface IUserGiftCardService {

    void addGiftCardByUser(RequestUserGiftCard requestUserGiftCard); // 유저가 카드 등록

    void chargeUsersGiftCard(RequestChargeUserGiftCard requestChargeUserGiftCard); // 유저가 가진 카드 중 1개 충전

    GiftCard getGiftCardByUser(RequestUserGiftCard requestUserGiftCard); // 유저가 가진 카드 중 1개 조회

    List<GiftCard> getAllGiftCardByUser(Long userId); // 유저가 가진 카드 전부 조회

//    void deleteUserGiftCard(RequestUserGiftCard requestUserGiftCard); // 유저가 가진 카드 중 1개 삭제
}