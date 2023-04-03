package spaland.products.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.vo.RequestCategoryList;
import spaland.products.vo.ResponseProduct;

import java.util.List;

public interface IProductCategoryListService {

    ResponseEntity<Message> addProductCategoryList(RequestCategoryList requestCategoryList);
    ResponseEntity<Message> getByCategoryLargeId(Integer categoryLargeId);
    ResponseEntity<Message> getByCategoryMiddleId(Integer categoryMiddleId);
    ResponseEntity<Message> getByProductOptionId(Integer productOptionId);
    ResponseEntity<Message> getByEventId(Integer eventId);

    ResponseEntity<Message> findAllByFilter(Specification<ProductCategoryList> spec);
}
