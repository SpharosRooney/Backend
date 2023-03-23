package spaland.products.repository_v2;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model_v2.TitleV2;

public interface ITitleRepositoryV2 extends JpaRepository<TitleV2, Long> {
    TitleV2 findByTitle(String title);
}
