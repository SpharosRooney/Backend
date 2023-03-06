package spaland.products.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RequestProduct {

    private String discription; // 상품 설명
    private String name; // 상품명
    private String titleImg; // 타이틀 이미지
    private String infoImg; // 정보 이미지
    private String infoImg2; // 정보 이미지
    private String infoImg3; // 정보 이미지
    private String opt; // 옵션
    private String category; // 카테고리
    private Integer price; // 가격
    private Integer inventory; // 재고
}
