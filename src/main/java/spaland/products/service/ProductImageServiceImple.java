package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductImage;
import spaland.products.repository.IProductImageRepository;
import spaland.products.vo.RequestProductImage;
import spaland.products.vo.ResponseProductImage;

import java.util.ArrayList;
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
    public ResponseProductImage getProductImage(Long productImageId) {
        return new ModelMapper().map(iProductImageRepository.findById(productImageId).get(), ResponseProductImage.class);
    }

    @Override
    public List<ResponseProductImage> getAllProductImage() {
        List<ProductImage> productImageList = iProductImageRepository.findAll();
        List<ResponseProductImage> responseProductImages = new ArrayList<>();

        productImageList.forEach(
                productImage -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductImages.add(
                            modelMapper.map(productImage,ResponseProductImage.class));
                });

        return responseProductImages;
    }
}
