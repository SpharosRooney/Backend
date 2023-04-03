package spaland.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.exception.CustomException;
import spaland.products.model.ProductSeason;
import spaland.products.repository.IProductSeasonRepository;
import spaland.products.vo.RequestProductSeason;
import spaland.products.vo.ResponseProductSeason;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_SEASON;

@RequiredArgsConstructor
@Service
public class ProductSeasonServiceImple implements IProductSeasonService {

    private final IProductSeasonRepository iProductSeasonRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<Message> addSeason(RequestProductSeason requestProductSeason) {
        iProductSeasonRepository.save(modelMapper.map(requestProductSeason, ProductSeason.class));
        Message message = new Message();
        message.setMessage("시즌 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProductSeason(Integer productSeasonId) {
        Message message = new Message();
        message.setMessage("시즌 조회 성공!");
        message.setData(modelMapper.map(iProductSeasonRepository.findById(productSeasonId).orElseThrow(() -> new CustomException(INVALID_SEASON)), ResponseProductSeason.class));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllProductSeason() {
        List<ProductSeason> productSeasonList = iProductSeasonRepository.findAll();
        List<ResponseProductSeason> responseProductSeasons = new ArrayList<>();
        productSeasonList.forEach(
                productSeason -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductSeasons.add(
                            modelMapper.map(productSeason, ResponseProductSeason.class)
                    );
                });
        Message message = new Message();
        message.setMessage("시즌 전체 조회 성공!");
        message.setData(responseProductSeasons);
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
