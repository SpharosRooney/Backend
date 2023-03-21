package spaland.products.model_v2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String season;
}

/*
커티스 쿨릭
체리블라썸
밸런타인데이
New Year
데스크 컬렉션
Christmas
여주자유CC
Autumn
시럽
테이블웨어 컬렉션
홈앤피크닉
파스텔 텀블러
아웃도어 컬렉션
Galaxy
선물세트
CASETiFY
라인프렌즈
Core
 */