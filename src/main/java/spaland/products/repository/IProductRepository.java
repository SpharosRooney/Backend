package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
