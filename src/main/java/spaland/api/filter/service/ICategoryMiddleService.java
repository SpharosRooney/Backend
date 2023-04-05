package spaland.api.filter.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.filter.vo.RequestCategoryMiddle;

public interface ICategoryMiddleService {

    ResponseEntity<Message> addCategory(RequestCategoryMiddle requestCategoryMiddle);
    ResponseEntity<Message> getCategoryMiddle(Integer categoryMiddleId);
    ResponseEntity<Message> getAll();

}
