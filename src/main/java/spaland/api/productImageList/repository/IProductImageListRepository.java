package spaland.api.productImageList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.productImageList.model.ProductImageList;

import java.util.List;

public interface IProductImageListRepository extends JpaRepository<ProductImageList, Long> {

    List<ProductImageList> findAllByProductId(Long productId);
}
