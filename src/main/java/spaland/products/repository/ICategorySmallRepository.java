package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.CategorySmall;

public interface ICategorySmallRepository extends JpaRepository<CategorySmall,Integer> {

}
