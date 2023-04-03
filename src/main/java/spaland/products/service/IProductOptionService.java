package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.ProductOption;
import spaland.products.vo.RequestProductOption;
import spaland.products.vo.ResponseProductOption;

import java.util.List;

public interface IProductOptionService {
    ResponseEntity<Message> addProductOption(RequestProductOption requestProductOption);
    ResponseEntity<Message> getProductOption(Integer productOptionId);
    ResponseEntity<Message> getAllProductOption();
}
