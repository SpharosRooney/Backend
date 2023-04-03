package spaland.products.service;

import spaland.products.model.CategoryMiddle;
import spaland.products.vo.RequestCategoryMiddle;
import spaland.products.vo.ResponseCategoryMiddle;

import java.util.List;

public interface ICategoryMiddleService {

    void addCategory(RequestCategoryMiddle requestCategoryMiddle);
    ResponseCategoryMiddle getCategoryMiddle(Integer categoryMiddleId);
    List<ResponseCategoryMiddle> getAll();

}
