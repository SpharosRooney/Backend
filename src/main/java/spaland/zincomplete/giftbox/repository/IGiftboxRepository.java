package spaland.zincomplete.giftbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.zincomplete.giftbox.model.Giftbox;

import java.util.List;

public interface IGiftboxRepository extends JpaRepository<Giftbox, Long> {
    List<Giftbox> findAllByUserId(Long userId);
    Giftbox save(Giftbox giftbox);
}
