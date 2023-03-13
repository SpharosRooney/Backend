package spaland.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.utility.BaseTimeEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
