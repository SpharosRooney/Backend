package spaland.products.service;

import org.springframework.http.ResponseEntity;
import spaland.Response.Message;
import spaland.products.vo.RequestProductSeason;
import spaland.products.vo.ResponseProductSeason;

import java.util.List;

public interface IProductSeasonService {
    ResponseEntity<Message> addSeason(RequestProductSeason requestProductSeason);
    ResponseEntity<Message> getProductSeason(Integer categoryLargeId);
    ResponseEntity<Message> getAllProductSeason();
}
