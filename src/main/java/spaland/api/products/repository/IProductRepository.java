package spaland.api.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.products.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
