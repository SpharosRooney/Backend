package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.CategoryMiddle;
import spaland.products.repository.ICategoryMiddleRepository;
import spaland.products.vo.RequestCategoryMiddle;

import java.util.List;

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
    public CategoryMiddle getCategoryMiddle(Integer categoryMiddleId) {

        return iCategoryMiddleRepository.findById(categoryMiddleId).get();
    }

    @Override
    public List<CategoryMiddle> getAll() {
        return iCategoryMiddleRepository.findAll();
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
