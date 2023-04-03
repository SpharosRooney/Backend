package spaland.zincomplete.wish.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Builder@Data
public class ResponseGetUserWish {
    private Long id;
    private Long userId;
    private Long productId;
}
