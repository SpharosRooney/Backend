package spaland.products.service;

import spaland.products.vo.RequestProductSeason;
import spaland.products.vo.ResponseProductSeason;

import java.util.List;

public interface IProductSeasonService {
    void addSeason(RequestProductSeason requestProductSeason);
    ResponseProductSeason getProductSeason(Integer categoryLargeId);
    List<ResponseProductSeason> getAllProductSeason();
}
