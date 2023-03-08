package spaland.giftCard.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.utility.BaseTimeEntity;

@Data //  @Getter, @Setter, @RequiredArgsConstructor, @ToString 포함된거
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer remaining;
    private String img_root;
    private String type;
}