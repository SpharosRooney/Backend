package spaland.api.ProductCategoryList.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.ProductCategoryList.model.ProductCategoryList;
import spaland.api.ProductCategoryList.vo.RequestCategoryList;

public interface IProductCategoryListService {

    ResponseEntity<Message> addProductCategoryList(RequestCategoryList requestCategoryList);
    ResponseEntity<Message> getByCategoryLargeId(Integer categoryLargeId);
    ResponseEntity<Message> getByCategoryMiddleId(Integer categoryMiddleId);
    ResponseEntity<Message> getByProductOptionId(Integer productOptionId);
    ResponseEntity<Message> getByEventId(Integer eventId);

    ResponseEntity<Message> findAllByFilter(Specification<ProductCategoryList> spec);
}
