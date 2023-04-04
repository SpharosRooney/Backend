package spaland.api.ProductCategoryList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import spaland.api.ProductCategoryList.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long>, JpaSpecificationExecutor<ProductCategoryList> {

//    List<ProductCategoryList> findByProductId(Long productId);
    List<ProductCategoryList> findByCategoryLargeId(Integer categoryLargeId);
    List<ProductCategoryList> findByCategoryMiddleId(Integer categoryMiddleId);
    List<ProductCategoryList> findByProductOptionId(Integer productOptionId);
    List<ProductCategoryList> findByProductSeasonId(Integer seasonId);
}
