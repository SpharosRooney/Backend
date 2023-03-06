package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.products.vo.RequestProduct;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImple implements IProductService{

    private final IProductRepository iProductRepository;

    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);
        iProductRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return iProductRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return iProductRepository.findAll();
    }
}
