package spaland.shipping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.shipping.model.ShippingAddress;
import spaland.shipping.repository.IShippingAddressRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingAddressServiceImple implements IShippingAddressService{
    private final IShippingAddressRepository iShippingAddressRepository;

    @Override
    public ShippingAddress addShippingAddress(ShippingAddress shippingAddress) {
        return iShippingAddressRepository.save(shippingAddress);
    }


    @Override
    public ShippingAddress getShippingAddress(Long ShippingAddressId) {
        return iShippingAddressRepository.findById(ShippingAddressId).get();
    }




    @Override
    public List<ShippingAddress> getAllShippingAddress() {
        return iShippingAddressRepository.findAll();
    }



    @Override
    public List<ShippingAddress> getAllByIsUse(Short ShippingAddressIsUse) {
        return iShippingAddressRepository.findByIsUse(ShippingAddressIsUse);
    }
}