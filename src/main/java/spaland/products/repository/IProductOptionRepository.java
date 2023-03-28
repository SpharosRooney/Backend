package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductOption;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}
