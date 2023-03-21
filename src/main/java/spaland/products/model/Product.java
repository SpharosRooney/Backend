package spaland.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.utility.BaseTimeEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 번호

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
    private String season; // 시즌
    private String title; // 대분류


}
