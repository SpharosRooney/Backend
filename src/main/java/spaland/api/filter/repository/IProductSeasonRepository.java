package spaland.api.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.filter.model.ProductSeason;

public interface IProductSeasonRepository extends JpaRepository<ProductSeason, Integer> {
}
