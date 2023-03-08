package spaland.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.shipping.model.UserShippingAddress;

import java.util.List;

public interface IUserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    UserShippingAddress save(UserShippingAddress userShippingAddress);
    List<UserShippingAddress> findAllByUserId(Long userId);
    List<UserShippingAddress> findAllByUserIdAndIsUse(Long userId, Boolean isUse);
}
