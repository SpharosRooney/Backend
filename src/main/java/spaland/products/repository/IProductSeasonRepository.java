package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductSeason;

public interface IProductSeasonRepository extends JpaRepository<ProductSeason, Integer> {
}
