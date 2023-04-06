package spaland.api.filter.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.filter.vo.RequestProductOption;

public interface IProductOptionService {
    ResponseEntity<Message> addProductOption(RequestProductOption requestProductOption);
    ResponseEntity<Message> getProductOption(Integer productOptionId);
    ResponseEntity<Message> getAllProductOption();
}
