package spaland.api.filter.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.filter.vo.RequestProductSort;

public interface IProductSortService {
    ResponseEntity<Message> addProductSort(RequestProductSort requestProductSort);
    ResponseEntity<Message> getProductSort(Integer productSortId);
    ResponseEntity<Message> getAllProductSort();
}
