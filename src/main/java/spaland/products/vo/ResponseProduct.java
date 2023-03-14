package spaland.products.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {

    private Long id; // 상품 번호
    private String name; // 상품명
    private String discription; // 상품 설명
    private Integer opt; // 옵션
    private Integer price; // 가격
    private Integer inventory; // 재고
    private String titleImg; // 타이틀 이미지
    private String infoImg; // 정보 이미지
    private String infoImg2; // 정보 이미지
    private String infoImg3; // 정보 이미지

}
