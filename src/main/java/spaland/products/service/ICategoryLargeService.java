package spaland.products.service;


import spaland.products.model.CategoryLarge;
import spaland.products.vo.RequestCategoryLarge;

import java.util.List;

public interface ICategoryLargeService {

    void addCategory(RequestCategoryLarge requestCategoryLarge);
    CategoryLarge getCategoryLarge(Integer categoryLargeId);
    List<CategoryLarge> getAll();
//    List<Category> getAllByType(String categoryType);

}
