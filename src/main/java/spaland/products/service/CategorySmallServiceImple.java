package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.CategorySmall;
import spaland.products.repository.ICategorySmallRepository;
import spaland.products.vo.RequestCategorySmall;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategorySmallServiceImple implements ICategorySmallService{

    private final ICategorySmallRepository iCategorySmallRepository;


    @Override
    public void addCategory(RequestCategorySmall requestCategorySmall) {
        ModelMapper modelMapper = new ModelMapper();
        CategorySmall categorySmall = modelMapper.map(requestCategorySmall, CategorySmall.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCategorySmallRepository.save(categorySmall);
    }

    @Override
    public CategorySmall getCategorySmall(Integer categoryId) {

        return iCategorySmallRepository.findById(categoryId).get();
    }

    @Override
    public List<CategorySmall> getAll() {
        return iCategorySmallRepository.findAll();
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
