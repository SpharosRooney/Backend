package spaland.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.shipping.model.ShippingAddress;

import java.util.List;

public interface IShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
    List<ShippingAddress> findByIsUse(Short shippingAddressIsUse);
}
