package spaland.products.service;

import spaland.products.dto.CategoryTypeDto;
import spaland.products.model.Category;
import spaland.products.vo.RequestCategory;

import java.util.List;

public interface ICategoryService {

    void addCategory(RequestCategory requestCategory);
    Category getCategory(Integer categoryId);
    List<Category> getAll();
    List<CategoryTypeDto> getAllByType(String categoryType);

    List<String> getTypeNames();
}
