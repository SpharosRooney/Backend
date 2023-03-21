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
public class VolumeV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long volume;

    /*
        237 short(ml)
        355 tall
        473 grande
        591 vanti
        916 trenta
     */
}