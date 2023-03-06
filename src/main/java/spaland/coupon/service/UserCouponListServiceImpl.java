package spaland.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.coupon.model.UserCouponList;
import spaland.coupon.repository.ICouponRepository;
import spaland.coupon.repository.IUserCouponListRepository;
import spaland.coupon.vo.RequestUserCouponList;
import spaland.users.repository.IUserRespository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCouponListServiceImpl implements IUserCouponListService {

    private final IUserCouponListRepository iUserCouponListRepository;
    private final IUserRespository iUserRespository;
    private final ICouponRepository iCouponRepository;

    @Override
    public void addUserCouponList(RequestUserCouponList requestUserCouponList) {

        UUID uuid = UUID.randomUUID();
        UserCouponList userCouponList = iUserCouponListRepository.save(UserCouponList.builder()
                .user(iUserRespository.findById(requestUserCouponList.getUserId()).get())
                .coupon(iCouponRepository.findById(requestUserCouponList.getCouponId()).get())
                .couponUUID(uuid.toString())
                .build());

        iUserCouponListRepository.save(userCouponList);

    }

    @Override
    public List<UserCouponList> getAllbyUserId(Long userId) {
        return iUserCouponListRepository.findAllByUserId(userId);
    }
}
