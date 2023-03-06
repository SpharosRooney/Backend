package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spaland.products.model.Category;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByType(String categoryType);

    @Query(value = "select type from category group by type", nativeQuery = true)
    List<String> groupByType();
}
