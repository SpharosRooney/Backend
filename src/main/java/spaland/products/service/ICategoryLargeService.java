package spaland.products.service;


import spaland.products.model.CategoryLarge;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.ResponseCategoryLarge;

import java.util.List;

public interface ICategoryLargeService {

    void addCategory(RequestCategoryLarge requestCategoryLarge);
    ResponseCategoryLarge getCategoryLarge(Integer categoryLargeId);
    List<ResponseCategoryLarge> getAll();
//    List<Category> getAllByType(String categoryType);

}
