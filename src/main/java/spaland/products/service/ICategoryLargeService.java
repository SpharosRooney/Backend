package spaland.products.service;


import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.CategoryLarge;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.ResponseCategoryLarge;

import java.util.List;

public interface ICategoryLargeService {

    ResponseEntity<Message> addCategory(RequestCategoryLarge requestCategoryLarge);
    ResponseEntity<Message> getCategoryLarge(Integer categoryLargeId);
    ResponseEntity<Message> getAll();

}
