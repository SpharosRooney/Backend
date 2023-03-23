package spaland.products.repository_v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import spaland.products.model_v2.Filter;

public interface IFilterRepositoryV2 extends JpaRepository<Filter,Long>, JpaSpecificationExecutor<Filter> {
}














