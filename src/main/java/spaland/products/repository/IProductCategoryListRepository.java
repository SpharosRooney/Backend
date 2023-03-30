package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import spaland.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long>, JpaSpecificationExecutor<ProductCategoryList> {

    List<ProductCategoryList> findByProductId(Long productId);
    List<ProductCategoryList> findByCategoryLargeId(Integer categoryLargeId);
    List<ProductCategoryList> findByCategoryMiddleId(Integer categoryMiddleId);
    List<ProductCategoryList> findByProductOptionId(Integer productOptionId);
    List<ProductCategoryList> findByEventId(Integer eventId);
//    List<ProductCategoryList> findByOptionName(String optionName);
//    List<ProductCategoryList> findByProductIdAndCategoryLargeIdAndCategoryMiddleIdAndProductOptionId(Long productId, Integer categoryLargeId, Integer categoryMiddleId, Integer productOptionId);

}
