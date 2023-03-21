package spaland.products.repository_v2;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model_v2.CategoryV2;

public interface ICategoryRepositoryV2 extends JpaRepository<CategoryV2, Long> {
    CategoryV2 findByCategory(String category);
}
