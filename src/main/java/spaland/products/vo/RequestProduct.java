package spaland.products.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RequestProduct {

    private String discription; // 상품 설명
    @Column(nullable = false)
    private String name; // 상품명
    private String titleImg; // 타이틀 이미지
    private String infoImg; // 정보 이미지
    private String infoImg2; // 정보 이미지
    private String infoImg3; // 정보 이미지
    private String opt; // 옵션
    private String category; // 카테고리
    @Column(nullable = false)
    private Integer price; // 가격
    private String season;
    private Integer inventory; // 재고
    private String title;
}
