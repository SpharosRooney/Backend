package spaland.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.coupon.model.Coupon2;
import spaland.coupon.repository.ICoupon2Repository;
import spaland.coupon.vo.RequestAddCoupon2;
import spaland.coupon.vo.ResponseCoupon2;
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Coupon2ServiceImpl implements ICoupon2Service {

    private final ICoupon2Repository iCoupon2Repository;
    private final IUserRepository iUserRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public ResponseCoupon2 addCouponByAdmin(RequestAddCoupon2 requestAddCoupon2) {
        // 1. 관리자가 특정 유저에게 쿠폰을 추가할 것임.
        // 2. userId를 통해 유저 객체를 알아온다.
        // 3. 이후 Coupon2 객체 빌드.
        iCoupon2Repository.save(
                Coupon2.builder()
                        .user(iUserRepository.findById(requestAddCoupon2.getUserId()).get())
                        .status(requestAddCoupon2.getStatus())
                        .percent(requestAddCoupon2.getPercent())
                        .name(requestAddCoupon2.getName())
                        .build()
        );
        return null;
    }

    @Override
    public ResponseCoupon2 getCoupon(Long couponId) {
        Coupon2 coupon2 = iCoupon2Repository.findById(couponId).get();
        return modelMapper.map(coupon2, ResponseCoupon2.class);
    }

    @Override
    public List<ResponseCoupon2> getAll(Long userId) {
        List<Coupon2> coupon2List = iCoupon2Repository.findAllByUserId(userId);
        List<ResponseCoupon2> responseCoupon2s = new ArrayList<>();

        coupon2List.forEach(
                coupon -> {
                    responseCoupon2s.add(
                            modelMapper.map(coupon, ResponseCoupon2.class)
                    );
                }
        );
        return responseCoupon2s;
    }

    @Override
    public ResponseCoupon2 useCoupon(Long couponId) {
        Coupon2 coupon2 = iCoupon2Repository.findById(couponId).get();
        coupon2.setUse(true);
        iCoupon2Repository.save(coupon2);
        return null;
    }

    @Override
    public ResponseCoupon2 refundCoupon(Long couponId) {
        Coupon2 coupon2 = iCoupon2Repository.findById(couponId).get();
        coupon2.setUse(false);
        iCoupon2Repository.save(coupon2);
        return null;
    }
}
