package spaland.products.repository_v2;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.products.model_v2.VolumeV2;

public interface IVolumeRepositoryV2 extends JpaRepository<VolumeV2, Long> {

//    VolumeV2 findByVolumeLessThanEqual(Long volume);
}
