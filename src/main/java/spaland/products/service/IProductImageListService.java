package spaland.products.service;

import spaland.products.model.ProductImage;
import spaland.products.model.ProductImageList;
import spaland.products.vo.RequestProductImageList;
import spaland.products.vo.ResponseProductImageList;

import java.util.List;

public interface IProductImageListService {
    void addProductImageList(RequestProductImageList requestProductImageList);
    ProductImageList getProductImageList(Long productImageListId);
    List<ResponseProductImageList> getProductImageListByProductId(Long productId);
    List<ProductImageList> getAllProductImageList();
}
