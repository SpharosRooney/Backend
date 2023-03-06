package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.dto.CategoryTypeDto;
import spaland.products.model.Category;
import spaland.products.repository.ICategoryRepository;
import spaland.products.vo.RequestCategory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImple implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;

//    public CategoryServiceImple(ICategoryRepository iCategoryRepository) {
//        this.iCategoryRepository = iCategoryRepository;
//    }

    @Override
    public void addCategory(RequestCategory requestCategory) {
        ModelMapper modelMapper = new ModelMapper();
        Category category = modelMapper.map(requestCategory, Category.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCategoryRepository.save(category);
    }

    @Override
    public Category getCategory(Integer categoryId) {
//        Category category = iCategoryRepository.findById(categoryId).get();
//        if(category != null) {
//            log.info("null");
//            return null;
//        }
        return iCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public List<CategoryTypeDto> getAllByType(String categoryType) {

        ModelMapper modelMapper = new ModelMapper();
        List<Category> categories = iCategoryRepository.findAllByType(categoryType);
        List<CategoryTypeDto> categoryTypeDtoList = new ArrayList<>();
        categories.forEach(category -> {
            categoryTypeDtoList.add(modelMapper.map(category, CategoryTypeDto.class));
        });
        return categoryTypeDtoList;
    }

    @Override
    public List<String> getTypeNames() {
        return iCategoryRepository.groupByType();
    }


}
