package spaland.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.products.model.ProductSeason;
import spaland.products.repository.IProductSeasonRepository;
import spaland.products.vo.RequestProductSeason;
import spaland.products.vo.ResponseProductSeason;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductSeasonServiceImple implements IProductSeasonService {

    private final IProductSeasonRepository iProductSeasonRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void addSeason(RequestProductSeason requestProductSeason) {
        iProductSeasonRepository.save(modelMapper.map(requestProductSeason, ProductSeason.class));
    }

    @Override
    public ResponseProductSeason getProductSeason(Integer productSeasonId) {
        return modelMapper.map(iProductSeasonRepository.findById(productSeasonId).get(), ResponseProductSeason.class);
    }

    @Override
    public List<ResponseProductSeason> getAllProductSeason() {
        List<ProductSeason> productSeasonList = iProductSeasonRepository.findAll();
        List<ResponseProductSeason> responseProductSeasons = new ArrayList<>();
        productSeasonList.forEach(
                productSeason -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductSeasons.add(
                            modelMapper.map(productSeason, ResponseProductSeason.class)
                    );
                });
        return responseProductSeasons;
    }
}
