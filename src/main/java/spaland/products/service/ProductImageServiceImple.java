package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductImage;
import spaland.products.repository.IProductImageRepository;
import spaland.products.vo.RequestProductImage;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageServiceImple implements IProductImageService{

    private final IProductImageRepository iProductImageRepository;

    @Override
    public void addProductImage(RequestProductImage requestProductImage) {
        iProductImageRepository.save(
                ProductImage.builder()
                        .imgUrl(requestProductImage.getImgUrl())
                        .imgAlt(requestProductImage.getImgAlt())
                        .build()
        );
    }

    @Override
    public ProductImage getProductImage(Long productImageId) {
        return iProductImageRepository.findById(productImageId).get();
    }

    @Override
    public List<ProductImage> getAllProductImage() {
        return iProductImageRepository.findAll();
    }
}
