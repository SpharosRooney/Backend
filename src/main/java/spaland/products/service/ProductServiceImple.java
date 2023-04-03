package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
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
    public ResponseEntity<Message> addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);
        iProductRepository.save(product);

        Message message = new Message();
        message.setMessage("상품 추가 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProduct(Long productId) {

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

        Message message = new Message();
        message.setMessage("상품 조회 성공!");
        message.setData(ResponseProduct.builder()
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
                .build());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllProduct() {
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
        Message message = new Message();
        message.setMessage("상품 전체 조회 성공!");
        message.setData(responseProductList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
