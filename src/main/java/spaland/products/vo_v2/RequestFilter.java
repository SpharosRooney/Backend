package spaland.products.vo_v2;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RequestFilter {

    @Column(nullable = false)
    private Long productId; // 상품 id
    private Long price; // 가격
    private Long season; // 시즌
    private Long category; // 카테고리
    private Long volume; // 용량
}
