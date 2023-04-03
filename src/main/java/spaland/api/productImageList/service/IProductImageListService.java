package spaland.api.productImageList.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.productImageList.vo.RequestProductImageList;

public interface IProductImageListService {
    ResponseEntity<Message> addProductImageList(RequestProductImageList requestProductImageList);
    ResponseEntity<Message> getProductImageList(Long productImageListId);
    ResponseEntity<Message> getProductImageListByProductId(Long productId);
    ResponseEntity<Message> getAllProductImageList();
}
