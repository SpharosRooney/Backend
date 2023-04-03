package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductImage;

public interface IProductImageRepository extends JpaRepository<ProductImage, Long>{
}
