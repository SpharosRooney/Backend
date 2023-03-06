package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.CategoryLarge;
import spaland.products.repository.ICategoryLargeRepository;
import spaland.products.vo.RequestCategoryLarge;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLargeServiceImple implements ICategoryLargeService{

    private final ICategoryLargeRepository iCategoryLargeRepository;


    @Override
    public void addCategory(RequestCategoryLarge requestCategoryLarge) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryLarge categoryLarge = modelMapper.map(requestCategoryLarge, CategoryLarge.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCategoryLargeRepository.save(categoryLarge);
    }

    @Override
    public CategoryLarge getCategoryLarge(Integer categoryLargeId) {
        return iCategoryLargeRepository.findById(categoryLargeId).get();
    }
        @Override
    public List<CategoryLarge> getAll() {
        return iCategoryLargeRepository.findAll();
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
