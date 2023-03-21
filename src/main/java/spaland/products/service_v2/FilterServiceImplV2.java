package spaland.products.service_v2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spaland.products.model.Product;
import spaland.products.model_v2.*;
import spaland.products.repository.IProductRepository;
import spaland.products.repository_v2.*;
import spaland.products.vo_v2.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilterServiceImplV2 implements IFilterServiceV2 {

    private final IProductRepository iProductRepository;
    private final ICategoryRepositoryV2 iCategoryRepositoryV2;
    private final ISeasonRepositoryV2 iSeasonRepositoryV2;
    private final IVolumeRepositoryV2 iVolumeRepositoryV2;
    private final ITitleRepositoryV2 iTitleRepositoryV2;
    private final IFilterRepositoryV2 iFilterRepositoryV2;

    @Override
    public void addSeason(RequestSeasonV2 requestSeasonV2) {
        iSeasonRepositoryV2.save(SeasonV2.builder()
                .season(requestSeasonV2.getSeason())
                .build());
    }

    @Override
    public void addCategory(RequestCategoryV2 requestCategoryV2) {
        iCategoryRepositoryV2.save(CategoryV2.builder()
                .category(requestCategoryV2.getCategory())
                .build());
    }

    @Override
    public void addVolume(RequestVolumeV2 requestVolumeV2) {
        iVolumeRepositoryV2.save(VolumeV2.builder()
                .volume(requestVolumeV2.getVolume())
                .build());
    }

    @Override
    public void addTitle(RequestTitleV2 requestTitleV2) {
        iTitleRepositoryV2.save(TitleV2.builder()
                .title(requestTitleV2.getTitle())
                .build());
    }

    @Override
    public List<Product> findAllWithFilter(Specification<Filter> spec) {
        List<Filter> all = iFilterRepositoryV2.findAll(spec);
        List<Long> productsId = all.stream().map(Filter::getProductId).collect(Collectors.toList());
        return iProductRepository.findAllById(productsId);
    }

    @Override
    public List<Product> findAllWithSearch(String name){
        return iProductRepository.findAllByNameContaining(name);
    }

}
