package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.products.model.CategoryMiddle;
import spaland.products.repository.ICategoryMiddleRepository;
import spaland.products.vo.RequestCategoryMiddle;
import spaland.products.vo.ResponseCategoryMiddle;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryMiddleServiceImple implements ICategoryMiddleService{

    private final ICategoryMiddleRepository iCategoryMiddleRepository;


    @Override
    public void addCategory(RequestCategoryMiddle requestCategoryMiddle) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryMiddle categoryMiddle = modelMapper.map(requestCategoryMiddle, CategoryMiddle.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCategoryMiddleRepository.save(categoryMiddle);
    }

    @Override
    public ResponseCategoryMiddle getCategoryMiddle(Integer categoryMiddleId) {

        return new ModelMapper().map(iCategoryMiddleRepository.findById(categoryMiddleId).orElseThrow(()-> new CustomException(INVALID_CATEGORY)),ResponseCategoryMiddle.class);
    }

    @Override
    public List<ResponseCategoryMiddle> getAll() {
        List<CategoryMiddle> categoryMiddleList =  iCategoryMiddleRepository.findAll();
        List<ResponseCategoryMiddle> responseCategoryMiddles = new ArrayList<>();

        categoryMiddleList.forEach(
                categoryMiddle -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseCategoryMiddles.add(
                            modelMapper.map(categoryMiddle,ResponseCategoryMiddle.class)
                    );
                });

        return responseCategoryMiddles;
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
