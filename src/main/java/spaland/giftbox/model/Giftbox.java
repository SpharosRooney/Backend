package spaland.giftbox.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import spaland.products.model.Product;
import spaland.users.model.User;
import spaland.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Giftbox extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private String sender;

    @Builder.Default
    private Integer state = 0;
    //처음에 선물 받으면 주문완료(0), 배송지 정보 입력하면 배송 준비(1), 배송 중(2), 배송 완료(3) 상태로 나타남, 환불 시 환불 완료(4)

    @Column(nullable = false)
    private Integer giftAmount;


    @Builder.Default
    private String letter = "스타벅스에서 선물이 도착했어요";



}
