package spaland.api.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.filter.model.ProductOption;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}
