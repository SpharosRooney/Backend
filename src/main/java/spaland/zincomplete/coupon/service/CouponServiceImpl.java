package spaland.zincomplete.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.zincomplete.coupon.model.Coupon;
import spaland.zincomplete.coupon.repository.ICouponRepository;
import spaland.zincomplete.coupon.vo.RequestCoupon;
import spaland.zincomplete.coupon.vo.ResponseCoupon;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService{

    private final ICouponRepository iCouponRepository;
    @Override
    public ResponseCoupon addCoupon(RequestCoupon requestCoupon) {

        Coupon coupon = Coupon.builder()
                .status(requestCoupon.getStatus())
                .percent(requestCoupon.getPercent())
                .name(requestCoupon.getName())
                .build();

        iCouponRepository.save(coupon);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(iCouponRepository.findByName(requestCoupon.getName()),ResponseCoupon.class);
    }

    @Override
    public ResponseCoupon getCoupon(Long couponId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(iCouponRepository.findById(couponId).get(), ResponseCoupon.class);
    }

    @Override
    public List<ResponseCoupon> getAll() {

        List<Coupon> couponList =  iCouponRepository.findAll();
        List<ResponseCoupon> responseCouponList = new ArrayList<>();

        couponList.forEach(
            coupon -> {
                ModelMapper modelMapper = new ModelMapper();
                responseCouponList.add(modelMapper.map(coupon,ResponseCoupon.class));
            }
        );

        return responseCouponList;
    }

    @Override
    public ResponseCoupon useCoupon(Long couponId) {
        ModelMapper modelMapper = new ModelMapper();
        Coupon coupon = modelMapper.map(iCouponRepository.findById(couponId).get(), Coupon.class);
        coupon.setUse(true);

        iCouponRepository.save(coupon);
        ModelMapper modelMapper2 = new ModelMapper();
        return modelMapper2.map(iCouponRepository.findById(couponId).get(),ResponseCoupon.class);
    }

    @Override
    public ResponseCoupon refundCoupon(Long couponId) {
        ModelMapper modelMapper = new ModelMapper();
        Coupon coupon = modelMapper.map(iCouponRepository.findById(couponId).get(), Coupon.class);
        coupon.setUse(false);

        iCouponRepository.save(coupon);
        ModelMapper modelMapper2 = new ModelMapper();
        return modelMapper2.map(iCouponRepository.findById(couponId).get(),ResponseCoupon.class);
    }
}
