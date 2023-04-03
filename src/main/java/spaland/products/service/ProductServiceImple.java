package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.products.model.ProductImage;
import spaland.products.model.ProductImageList;
import spaland.products.model.Product;
import spaland.products.repository.IProductImageListRepository;
import spaland.products.repository.IProductImageRepository;
import spaland.products.repository.IProductRepository;
import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_PRODUCT;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImple implements IProductService {

    private final IProductRepository iProductRepository;
    private final IProductImageListRepository iProductImageListRepository;

    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);
        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long productId) {

        Product product = iProductRepository.findById(productId).orElseThrow(() -> new CustomException(INVALID_PRODUCT));
        List<ProductImageList> productImageList = iProductImageListRepository.findAllByProductId(product.getId());
        log.info("productImageList: {}", productImageList);
        List<ProductImage> productImages = new ArrayList<>();
        productImageList.forEach(
                productImageLists -> {
                    productImages.add(productImageLists.getProductImage());
                }
        );
        log.info("productImages: {}", productImages);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        return ResponseProduct.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .productImageList(productImages)
                .inventory(product.getInventory())
                .id(product.getId())
                .titleImg(product.getTitleImg())
                .frozen(product.getFrozen())
                .salesQuantity(product.getSalesQuantity())
                .isNew(product.getUpdateTime().isBefore(oneMonthAgo) ? false : true)
                .build();
    }

    @Override
    public List<ResponseProduct> getAllProduct() {
        List<Product> productList = iProductRepository.findAll();
        List<ResponseProduct> responseProductList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        productList.forEach(
                product -> {
                    ModelMapper modelMapper = new ModelMapper();
                    ResponseProduct temp = modelMapper.map(product, ResponseProduct.class);
                    temp.setIsNew(product.getUpdateTime().isBefore(oneMonthAgo) ? false : true);
                    responseProductList.add(temp);
                }
        );
        return responseProductList;
    }
}
