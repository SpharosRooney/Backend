package spaland.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.coupon.model.Coupon;
import spaland.coupon.repository.ICouponRepository;
import spaland.coupon.vo.RequestCoupon;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService{

    private final ICouponRepository iCouponRepository;

    @Override
    public void addCoupon(RequestCoupon requestCoupon) {
        System.out.println("name : " + requestCoupon.getName());
        ModelMapper modelMapper = new ModelMapper();
        Coupon coupon = modelMapper.map(requestCoupon, Coupon.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCouponRepository.save(coupon);
    }
    @Override
    public Coupon getCoupon(Long couponId) {
        return iCouponRepository.findById(couponId).get();
    }

    @Override
    public List<Coupon> getAll() {
        return iCouponRepository.findAll();
    }
}
