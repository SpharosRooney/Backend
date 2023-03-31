package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.products.model.ProductImageList;
import spaland.products.repository.IProductImageListRepository;
import spaland.products.repository.IProductImageRepository;
import spaland.products.repository.IProductRepository;
import spaland.products.vo.RequestProductImageList;
import spaland.products.vo.ResponseProductImageList;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_IMAGE_LIST;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageListServiceImple implements IProductImageListService{

    private final IProductImageListRepository iProductImageListRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepository iProductImageRepository;

    @Override
    public void addProductImageList(RequestProductImageList requestProductImageList) {
        iProductImageListRepository.save(
                ProductImageList.builder()
                        .product(iProductRepository.findById(requestProductImageList.getProductId()).get())
                        .productImage(iProductImageRepository.findById(requestProductImageList.getProductImageId()).get())
                        .build()
        );
    }

    @Override
    public ProductImageList getProductImageList(Long productImageListId) {
        return iProductImageListRepository.findById(productImageListId).orElseThrow(() -> new CustomException(INVALID_IMAGE_LIST));
    }

    @Override
    public List<ResponseProductImageList> getProductImageListByProductId(Long productId) {
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

        return responseProductImageLists;
    }

    @Override
    public List<ProductImageList> getAllProductImageList() {
        return iProductImageListRepository.findAll();
    }
}
