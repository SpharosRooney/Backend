package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductImageList;

import java.util.List;

public interface IProductImageListRepository extends JpaRepository<ProductImageList, Long> {

    List<ProductImageList> findAllByProductId(Long productId);
}
