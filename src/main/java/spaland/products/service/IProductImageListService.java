package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.ProductImage;
import spaland.products.model.ProductImageList;
import spaland.products.vo.RequestProductImageList;
import spaland.products.vo.ResponseProductImageList;

import java.util.List;

public interface IProductImageListService {
    ResponseEntity<Message> addProductImageList(RequestProductImageList requestProductImageList);
    ResponseEntity<Message> getProductImageList(Long productImageListId);
    ResponseEntity<Message> getProductImageListByProductId(Long productId);
    ResponseEntity<Message> getAllProductImageList();
}
