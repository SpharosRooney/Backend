package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spaland.products.model.CategoryLarge;
import spaland.products.model.Event;
import spaland.exception.CustomException
import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.repository.*;
import spaland.products.vo.RequestCategoryList;
import spaland.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static spaland.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImple implements IProductCategoryListService {

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductRepository iProductRepository;
    private final ICategoryLargeRepository iCategoryLargeRepository;
    private final ICategoryMiddleRepository iCategoryMiddleRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final IProductSeasonRepository iProductSeasonRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addProductCategoryList(RequestCategoryList requestCategoryList) {

        Optional<Product> byId = iProductRepository.findById(requestCategoryList.getProductId());
        Optional<CategoryLarge> byId1 = iCategoryLargeRepository.findById(requestCategoryList.getCategoryLargeId());

        iProductCategoryListRepository.save(
                ProductCategoryList.builder()
                        .product(iProductRepository.findById(requestCategoryList.getProductId()).get())
                        .categoryLarge(iCategoryLargeRepository.findById(requestCategoryList.getCategoryLargeId()).get())
                        .categoryMiddle(getValue(iCategoryMiddleRepository.findById(requestCategoryList.getCategoryMiddleId())))
                        .productOption(getValue(iProductOptionRepository.findById(requestCategoryList.getProductOptionId())))
                        .productSeason(getValue(iProductSeasonRepository.findById(requestCategoryList.getProductSeasonId())))
                        .event(getValue(iEventRepository.findById(requestCategoryList.getEventId())))
                        .build()
        );
    }

    public static <T> T getValue(Optional<T> data) {
        return data.isPresent() ? data.get() : null;
    }

    @Override
    public List<ResponseProduct> getByCategoryLargeId(Integer categoryLargeId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryLargeId(categoryLargeId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByCategoryMiddleId(Integer categoryMiddleId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryMiddleId(categoryMiddleId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByProductOptionId(Integer productOptionId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductOptionId(productOptionId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByEventId(Integer eventId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByEventId(eventId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<Product> findAllByFilter(Specification<ProductCategoryList> spec) {
        List<ProductCategoryList> all = iProductCategoryListRepository.findAll(spec);
        List<Product> productList = new ArrayList<Product>();
        for (ProductCategoryList iter : all) {
            productList.add(iter.getProduct());
        }
        return productList;
    }
}
