package spaland.products.service;

import spaland.products.model.ProductOption;
import spaland.products.vo.RequestProductOption;
import spaland.products.vo.ResponseProductOption;

import java.util.List;

public interface IProductOptionService {
    void addProductOption(RequestProductOption requestProductOption);
    ResponseProductOption getProductOption(Integer productOptionId);
    List<ResponseProductOption> getAllProductOption();
}
