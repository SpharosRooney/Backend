package spaland.products.service;

import spaland.products.model.CategorySmall;
import spaland.products.vo.RequestCategorySmall;

import java.util.List;

public interface ICategorySmallService {

    void addCategory(RequestCategorySmall requestCategorySmall);
    CategorySmall getCategorySmall(Integer categoryId);
    List<CategorySmall> getAll();
//    List<Category> getAllByType(String categoryType);

}
