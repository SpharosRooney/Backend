package spaland.api.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.image.model.ProductImage;

public interface IProductImageRepository extends JpaRepository<ProductImage, Long>{
}
