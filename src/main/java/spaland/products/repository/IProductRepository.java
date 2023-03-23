package spaland.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContaining(String name);
    Product findByName(String name);
}
