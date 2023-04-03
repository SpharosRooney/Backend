package spaland.api.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.filter.model.CategoryLarge;

public interface ICategoryLargeRepository extends JpaRepository<CategoryLarge, Integer> {
}
