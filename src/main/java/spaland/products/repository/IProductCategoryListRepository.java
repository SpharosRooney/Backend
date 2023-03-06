package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductCategoryList;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
}
