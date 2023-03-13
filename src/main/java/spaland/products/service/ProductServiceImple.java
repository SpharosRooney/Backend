package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImple implements IProductService{

    private final IProductRepository iProductRepository;





    @Override
    public ResponseProduct addProduct(RequestProduct requestProduct) {

        Product product = Product.builder()
                .name(requestProduct.getName())
                .discription(requestProduct.getDiscription())
                .opt(requestProduct.getOpt())
                .price(requestProduct.getPrice())
                .inventory(requestProduct.getInventory())
                .titleImg(requestProduct.getTitleImg())
                .infoImg(requestProduct.getInfoImg())
                .infoImg2(requestProduct.getInfoImg2())
                .infoImg3(requestProduct.getInfoImg3())
                .build();

        iProductRepository.save(product);
        iProductRepository.findByName(requestProduct.getName());

        ModelMapper modelMapper = new ModelMapper();


        return modelMapper.map(iProductRepository.findByName(requestProduct.getName()), ResponseProduct.class);
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
