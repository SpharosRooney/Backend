package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {

    List<ProductCategoryList> findByProductId(Long productId);
    List<ProductCategoryList> findByCategoryLargeId(Long categoryLargeId);
    List<ProductCategoryList> findByCategoryMiddleId(Long categoryMiddleId);
    List<ProductCategoryList> findByProductOptionId(Long productOptionId);
    List<ProductCategoryList> findByEventId(Long eventId);

}
