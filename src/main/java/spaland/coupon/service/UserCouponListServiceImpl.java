package spaland.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper = new ModelMapper();

//    @Override
//    public void addUserCouponList(RequestUserCouponList requestUserCouponList) {
//
//        UserCouponList userCouponList = iUserCouponListRepository.save(UserCouponList.builder()
//                .user(iUserRespository.findById(requestUserCouponList.getUserId()).get())
//                .coupon(iCouponRepository.findById(requestUserCouponList.getCouponId()).get())
//                .build());
//
//        iUserCouponListRepository.save(userCouponList);
//
//    }

    @Override // 관리자가 직접 쿠폰 부여.. userID 받아오고, 쿠폰을 생성해야하나?, 여기 완성해야함
    public void addCouponByAdmin(RequestUserCouponList requestUserCouponList) {
        iUserCouponListRepository.save(UserCouponList.builder()
                        .user(iUserRespository.findById(requestUserCouponList.getUserId()).get())
                        .build();
    }

    @Override
    public List<UserCouponList> getAllbyUserId(Long userId) {
        return iUserCouponListRepository.findAllByUserId(userId);
    }
}
