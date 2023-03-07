package spaland.giftbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftbox.model.Giftbox;

import java.util.List;

public interface IGiftboxRepository extends JpaRepository<Giftbox, Long> {
    List<Giftbox> findAllByUserId(Long userId);
    List<Giftbox> findAllByProductId(Long productId);

    Giftbox save(Giftbox giftbox);
}
