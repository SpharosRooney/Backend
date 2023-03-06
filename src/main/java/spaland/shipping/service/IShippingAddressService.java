package spaland.shipping.service;

import spaland.shipping.model.ShippingAddress;

import java.util.List;

public interface IShippingAddressService {
    ShippingAddress addShippingAddress(ShippingAddress shippingAddress);

    ShippingAddress getShippingAddress(Long ShippingAddressId);

    List<ShippingAddress> getAllShippingAddress();

    List<ShippingAddress> getAllByIsUse(Short ShippingAddressIsUse);


}
