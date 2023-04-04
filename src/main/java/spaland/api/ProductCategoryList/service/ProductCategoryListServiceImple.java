package spaland.api.ProductCategoryList.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.ProductCategoryList.repository.IProductCategoryListRepository;
import spaland.api.event.repository.IEventRepository;
import spaland.api.filter.repository.ICategoryLargeRepository;
import spaland.api.filter.repository.ICategoryMiddleRepository;
import spaland.api.filter.repository.IProductOptionRepository;
import spaland.api.filter.repository.IProductSeasonRepository;
import spaland.api.products.model.Product;
import spaland.api.ProductCategoryList.model.ProductCategoryList;
import spaland.api.products.repository.*;
import spaland.api.ProductCategoryList.vo.RequestCategoryList;
import spaland.api.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<Message> addProductCategoryList(RequestCategoryList requestCategoryList) {

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

        Message message = new Message();
        message.setData("해당 상품의 카테고리 등록 성공");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: 2023-04-03 이건 뭔가요!
    public static <T> T getValue(Optional<T> data) {
        return data.isPresent() ? data.get() : null;
    }

    @Override // TODO: 2023-04-03 여기 로직 손 봐야합니다!
    public ResponseEntity<Message> getByCategoryLargeId(Integer categoryLargeId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryLargeId(categoryLargeId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });
        Message message = new Message();
        message.setData(productCategoryLists);
        message.setMessage("카테고리로 상품 찾기 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getByCategoryMiddleId(Integer categoryMiddleId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryMiddleId(categoryMiddleId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });
        Message message = new Message();
        message.setData(responseProduct);
        message.setMessage("카테고리로 상품 찾기 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getByProductOptionId(Integer productOptionId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductOptionId(productOptionId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });

        Message message = new Message();
        message.setData(responseProduct);
        message.setMessage("옵션으로 상품 찾기 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getByProductSeasonId(Integer seasonId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductSeasonId(seasonId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(), ResponseProduct.class));
                });
        Message message = new Message();
        message.setData(responseProduct);
        message.setMessage("시즌으로 상품 찾기 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> findAllByFilter(Specification<ProductCategoryList> spec) {
        List<ProductCategoryList> all = iProductCategoryListRepository.findAll(spec);
        List<Product> productList = new ArrayList<Product>();
        for (ProductCategoryList iter : all) {
            productList.add(iter.getProduct());
        }
        Message message = new Message();
        message.setData(productList);
        message.setMessage("필터로 상품 찾기 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
