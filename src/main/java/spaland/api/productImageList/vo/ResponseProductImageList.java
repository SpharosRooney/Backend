package spaland.api.productImageList.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.api.image.model.ProductImage;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductImageList {

    private Long productImageListId;
    private ProductImage productImage;
}
