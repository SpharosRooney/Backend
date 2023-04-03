package spaland.api.filter.service;


import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.filter.vo.RequestCategoryLarge;

public interface ICategoryLargeService {

    ResponseEntity<Message> addCategory(RequestCategoryLarge requestCategoryLarge);
    ResponseEntity<Message> getCategoryLarge(Integer categoryLargeId);
    ResponseEntity<Message> getAll();

}
