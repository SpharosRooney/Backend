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
import spaland.products.repository.IProductImageRepository;
import spaland.products.vo.RequestProductImage;
import spaland.products.vo.ResponseProductImage;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_IMAGE;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageServiceImple implements IProductImageService{

    private final IProductImageRepository iProductImageRepository;

    @Override
    public ResponseEntity<Message> addProductImage(RequestProductImage requestProductImage) {
        iProductImageRepository.save(
                ProductImage.builder()
                        .imgUrl(requestProductImage.getImgUrl())
                        .imgAlt(requestProductImage.getImgAlt())
                        .build()
        );
        
        Message message = new Message();
        message.setMessage("상품 이미지 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProductImage(Long productImageId) {
        Message message = new Message();
        message.setMessage("상품 이미지 조회 성공!");
        message.setData(new ModelMapper().map(iProductImageRepository.findById(productImageId).orElseThrow(() -> new CustomException(INVALID_IMAGE)), ResponseProductImage.class));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllProductImage() {
        List<ProductImage> productImageList = iProductImageRepository.findAll();
        List<ResponseProductImage> responseProductImages = new ArrayList<>();

        productImageList.forEach(
                productImage -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductImages.add(
                            modelMapper.map(productImage,ResponseProductImage.class));
                });
        Message message = new Message();
        message.setMessage("상품 이미지 전체 조회 성공!");
        message.setData(responseProductImages);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
