package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.CategoryMiddle;
import spaland.products.vo.RequestCategoryMiddle;
import spaland.products.vo.ResponseCategoryMiddle;

import java.util.List;

public interface ICategoryMiddleService {

    ResponseEntity<Message> addCategory(RequestCategoryMiddle requestCategoryMiddle);
    ResponseEntity<Message> getCategoryMiddle(Integer categoryMiddleId);
    ResponseEntity<Message> getAll();

}
