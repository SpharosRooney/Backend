package spaland.api.productImageList.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.products.repository.IProductRepository;
import spaland.api.productImageList.vo.RequestProductImageList;
import spaland.api.productImageList.vo.ResponseProductImageList;
import spaland.exception.CustomException;
import spaland.api.productImageList.model.ProductImageList;
import spaland.api.productImageList.repository.IProductImageListRepository;
import spaland.api.image.repository.IProductImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static spaland.exception.ErrorCode.INVALID_IMAGE_LIST;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageListServiceImple implements IProductImageListService{

    private final IProductImageListRepository iProductImageListRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepository iProductImageRepository;

    @Override
    public ResponseEntity<Message> addProductImageList(RequestProductImageList requestProductImageList) {
        iProductImageListRepository.save(
                ProductImageList.builder()
                        .product(iProductRepository.findById(requestProductImageList.getProductId()).get())
                        .productImage(iProductImageRepository.findById(requestProductImageList.getProductImageId()).get())
                        .build()
        );
        Message message = new Message();
        message.setMessage("이미지 리스트 등록 성공!");
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProductImageListByProductId(Long productId) {
        List<ProductImageList> productImageLists = iProductImageListRepository.findAllByProductId(productId);
        List<ResponseProductImageList> responseProductImageLists = new ArrayList<>();

        productImageLists.forEach(
                productImageList -> {
                    responseProductImageLists.add(
                            ResponseProductImageList.builder()
                                    .productImageListId(productImageList.getId())
                                    .productImage(productImageList.getProductImage())
                                    .build()
                    );
                }
        );
        Message message = new Message();
        message.setMessage("상품 번호를 통한 이미지 리스트 조회 성공!");
        message.setData(responseProductImageLists);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
