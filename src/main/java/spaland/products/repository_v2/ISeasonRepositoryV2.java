package spaland.products.repository_v2;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model_v2.SeasonV2;

public interface ISeasonRepositoryV2 extends JpaRepository<SeasonV2, Long> {
    SeasonV2 findBySeason(String season);
}
