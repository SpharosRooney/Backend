package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductOption;
import spaland.products.repository.IProductOptionRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductOptionServiceImple implements IProductOptionService{

    private final IProductOptionRepository iProductOptionRepository;

    @Override
    public void addProductOption(ProductOption productOption) {
        iProductOptionRepository.save(productOption);
    }

    @Override
    public ProductOption getProductOption(Integer productOptionId) {
        return iProductOptionRepository.findById(productOptionId).get();
    }

    @Override
    public List<ProductOption> getAllProductOption() {
        return iProductOptionRepository.findAll();
    }
}
