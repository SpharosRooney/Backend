package spaland.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.shipping.model.UserShippingAddressList;

import java.util.List;

public interface IUserShippingAddressListRepository extends JpaRepository<UserShippingAddressList, Long> {
    List<UserShippingAddressList> findAllByUserId(Long userId);
//    List<UserShippingAddressList> findAllByShippingAddressId(Long shippingAddressId);

}
