package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spaland.products.model_v2.Filter;
import spaland.products.model.Product;
import spaland.products.repository_v2.*;
import spaland.products.repository.IProductRepository;
import spaland.products.service_v2.IFilterServiceV2;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImple implements IProductService {

    private final IProductRepository iProductRepository;
    private final IFilterRepositoryV2 iFilterRepositoryV2;
    private final ITitleRepositoryV2 iTitleRepositoryV2;
    private final ISeasonRepositoryV2 iSeasonRepositoryV2;
    private final ICategoryRepositoryV2 iCategoryRepositoryV2;
//    private final IVolumeRepositoryV2 iVolumeRepositoryV2;

    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);

        String productName = requestProduct.getName();
        String seasonName = requestProduct.getSeason();

        int mlIdx = productName.indexOf("ml");

        Long volumeIdx = null;
        if (mlIdx != -1) {
            Long volume = Long.valueOf(0);
            Integer power10 = 1;
            for (mlIdx--; productName.charAt(mlIdx) != ' '; mlIdx--) {
                if (productName.charAt(mlIdx) == '/') break;
                volume += (productName.charAt(mlIdx) - '0') * power10;
                power10 *= 10;
            }
            if (productName.charAt(mlIdx) != '/' && volume > 0) { //콜드컵 SIP 리드 473/591/710ml 때문에 넣었습니다
//                volumeIdx = iVolumeRepositoryV2.findByVolumeLessThanEqual(volume).getId();
                if (916 <= volume) volumeIdx = 5L;
                else if (591 <= volume) volumeIdx = 4L;
                else if (473 <= volume) volumeIdx = 3L;
                else if (355 <= volume) volumeIdx = 2L;
                else volumeIdx = 1L;
            }
        }

        iFilterRepositoryV2.save(Filter.builder()
                .title(iTitleRepositoryV2.findByTitle(requestProduct.getTitle()).getId())
                .price((long) (product.getPrice() / 10000))
                .season(iSeasonRepositoryV2.findBySeason(seasonName).getId())
                .category(requestProduct.getCategory() == null ? null : iCategoryRepositoryV2.findByCategory(requestProduct.getCategory()).getId())
                .volume(volumeIdx)
                .productId(iProductRepository.save(product).getId())
                .build());
    }

//    @Async
//    @Override
//    public Product purchase(Long productId, int productNum) {
//
//        Product product = Product.builder()
//                .name(iProductRepository.findById(productId).get().getName())
//                .price(iProductRepository.findById(productId).get().getPrice() * productNum)
//                .infoImg(iProductRepository.findById(productId).get().getInfoImg())
//                .build();
//
//        iProductRepository.save(product);
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(iProductRepository.findByName(requestProduct.getName()), ResponseProduct.class);
//    }

    @Override
    public ResponseProduct getProduct(Long productId) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(iProductRepository.findById(productId).get(), ResponseProduct.class);
    }

    @Override
    public List<ResponseProduct> getAllProduct() {
        List<Product> productList = iProductRepository.findAll();
        List<ResponseProduct> responseProductList = new ArrayList<>();

        productList.forEach(
            product -> {
                ModelMapper modelMapper = new ModelMapper();
                responseProductList.add(
                        modelMapper.map(product, ResponseProduct.class)
                );
            }
        );
        return responseProductList;
    }
}
