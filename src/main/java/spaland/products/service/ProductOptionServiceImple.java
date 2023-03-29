package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductOption;
import spaland.products.repository.IProductOptionRepository;
import spaland.products.vo.RequestProductOption;
import spaland.products.vo.ResponseProductOption;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductOptionServiceImple implements IProductOptionService{

    private final IProductOptionRepository iProductOptionRepository;

    @Override
    public void addProductOption(RequestProductOption productOption) {
        iProductOptionRepository.save(new ModelMapper().map(productOption, ProductOption.class));
    }

    @Override
    public ResponseProductOption getProductOption(Integer productOptionId) {
        return new ModelMapper().map(iProductOptionRepository.findById(productOptionId).get(),ResponseProductOption.class);
    }

    @Override
    public List<ResponseProductOption> getAllProductOption() {
        List<ProductOption> productOptionList = iProductOptionRepository.findAll();
        List<ResponseProductOption> responseProductOptions = new ArrayList<>();

        productOptionList.forEach(
                productOption -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductOptions.add(
                            modelMapper.map(productOption,ResponseProductOption.class)
                    );
                });
        return responseProductOptions;
    }
}
