package spaland.api.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.filter.model.ProductSort;

public interface IProductSortRepository extends JpaRepository<ProductSort, Integer> {
}
