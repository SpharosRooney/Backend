package spaland.giftbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftbox.model.Giftbox;
import spaland.products.model.Product;
import spaland.shipping.model.UserShippingAddress;

import java.util.List;

public interface IGiftboxRepository extends JpaRepository<Giftbox, Long> {
    List<Giftbox> findAllByUserId(Long userId);
    Giftbox save(Giftbox giftbox);
}
