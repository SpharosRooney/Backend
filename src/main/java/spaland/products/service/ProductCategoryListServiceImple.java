package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductCategoryList;
import spaland.products.repository.*;
import spaland.products.vo.RequestCategoryList;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImple implements IProductCategoryListService {

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductRepository iProductRepository;
    private final ICategoryLargeRepository iCategoryLargeRepository;
    private final ICategoryMiddleRepository iCategoryMiddleRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addProductCategoryList(RequestCategoryList requestCategoryList) {

        iProductCategoryListRepository.save(
                ProductCategoryList.builder()
                        .categoryLarge(iCategoryLargeRepository.findById(requestCategoryList.getCategoryLargeId()).get())
                        .categoryMiddle(iCategoryMiddleRepository.findById(requestCategoryList.getCategoryMiddleId()).get())
                        .product(iProductRepository.findById(requestCategoryList.getProductId()).get())
                        .productOption(iProductOptionRepository.findById(requestCategoryList.getProductOptionId()).get())
                        .event(iEventRepository.findById(requestCategoryList.getEventId()).get())
                        .build()
        );

    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductId(productId);
        return productCategoryLists;
    }

    @Override
    public List<ProductCategoryList> getByCategoryLargeId(Long categoryLargeId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryLargeId(categoryLargeId);
        return productCategoryLists;
    }

    @Override
    public List<ProductCategoryList> getByCategoryMiddleId(Long categoryMiddleId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryMiddleId(categoryMiddleId);
        return productCategoryLists;
    }

    @Override
    public List<ProductCategoryList> getByProductOptionId(Long productOptionId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductOptionId(productOptionId);
        return productCategoryLists;
    }

    @Override
    public List<ProductCategoryList> getByEventId(Long eventId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByEventId(eventId);
        return productCategoryLists;
    }
}
