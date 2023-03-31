package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.products.model.CategoryLarge;
import spaland.products.repository.ICategoryLargeRepository;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.ResponseCategoryLarge;
import spaland.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLargeServiceImple implements ICategoryLargeService{

    private final ICategoryLargeRepository iCategoryLargeRepository;


    @Override
    public void addCategory(RequestCategoryLarge requestCategoryLarge) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryLarge categoryLarge = modelMapper.map(requestCategoryLarge, CategoryLarge.class);
        iCategoryLargeRepository.save(categoryLarge);
    }

    @Override
    public ResponseCategoryLarge getCategoryLarge(Integer categoryLargeId) {
        return new ModelMapper().map(iCategoryLargeRepository.findById(categoryLargeId).orElseThrow(()-> new CustomException(INVALID_CATEGORY)), ResponseCategoryLarge.class);
    }
        @Override
    public List<ResponseCategoryLarge> getAll() {
            List<CategoryLarge> categoryLargeList = iCategoryLargeRepository.findAll();
            List<ResponseCategoryLarge> responseCategoryLarges = new ArrayList<>();
            categoryLargeList.forEach(
                    categoryLarge -> {
                        ModelMapper modelMapper = new ModelMapper();
                        responseCategoryLarges.add(
                                modelMapper.map(categoryLarge, ResponseCategoryLarge.class)
                        );
                    });
        return responseCategoryLarges;
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
