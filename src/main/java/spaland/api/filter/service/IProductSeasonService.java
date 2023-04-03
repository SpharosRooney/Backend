package spaland.api.filter.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.api.filter.vo.RequestProductSeason;

public interface IProductSeasonService {
    ResponseEntity<Message> addSeason(RequestProductSeason requestProductSeason);
    ResponseEntity<Message> getProductSeason(Integer categoryLargeId);
    ResponseEntity<Message> getAllProductSeason();
}
