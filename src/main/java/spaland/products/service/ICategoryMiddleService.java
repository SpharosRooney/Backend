package spaland.products.service;

import spaland.products.model.CategoryMiddle;
import spaland.products.vo.RequestCategoryMiddle;

import java.util.List;

public interface ICategoryMiddleService {

    void addCategory(RequestCategoryMiddle requestCategoryMiddle);
    CategoryMiddle getCategoryMiddle(Integer categoryMiddleId);
    List<CategoryMiddle> getAll();
//    List<Category> getAllByType(String categoryType);

}
