package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import spaland.products.model.ProductImage;
import spaland.products.model.ProductImageList;

import spaland.products.model.Product;
import spaland.products.repository.IProductImageListRepository;

import spaland.products.repository.IProductRepository;

import spaland.products.vo.RequestProduct;
import spaland.products.vo.ResponseProduct;
import java.util.ArrayList;
import java.util.List;

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

        Product product = iProductRepository.findById(productId).get();
        List<ProductImageList> productImageList = iProductImageListRepository.findAllByProductId(product.getId());
        log.info("productImageList: {}", productImageList);
        List<ProductImage> productImages = new ArrayList<>();
        productImageList.forEach(
                productImageLists -> {
                    productImages.add(productImageLists.getProductImage());
                }
        );
        log.info("productImages: {}", productImages);

        return ResponseProduct.builder()
                .discription(product.getDiscription())
                .name(product.getName())
                .price(product.getPrice())
                .productImageList(productImages)
                .inventory(product.getInventory())
                .id(product.getId())
                .opt(product.getOpt())
                .titleImg(product.getTitleImg())
                .build();
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
