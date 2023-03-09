package spaland.products.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    @Override
    public Product purchase(Long productId, int productNum) {

        Product product = Product.builder()
                .name(iProductRepository.findById(productId).get().getName())
                .price(iProductRepository.findById(productId).get().getPrice()*productNum)
                .infoImg(iProductRepository.findById(productId).get().getInfoImg())
                .build();
        // 리턴하는 값을 VO로 바꿔도 좋겠음.
        return product;
    }
}
