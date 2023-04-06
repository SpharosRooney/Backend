package spaland.api.filter.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.filter.model.ProductSeason;
import spaland.api.filter.model.ProductSort;
import spaland.api.filter.repository.IProductSortRepository;
import spaland.api.filter.vo.RequestProductSort;
import spaland.api.filter.vo.ResponseProductSeason;
import spaland.api.filter.vo.ResponseProductSort;
import spaland.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_SEASON;

@RequiredArgsConstructor
@Service
public class ProductSortServiceImple implements IProductSortService{

    private final IProductSortRepository iProductSortRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<Message> addProductSort(RequestProductSort requestProductSort) {
        iProductSortRepository.save(modelMapper.map(requestProductSort, ProductSort.class));
        Message message = new Message();
        message.setMessage("정렬 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProductSort(Integer productSortId) {
        Message message = new Message();
        message.setMessage("정렬 조회 성공!");
        message.setData(modelMapper.map(iProductSortRepository.findById(productSortId).orElseThrow(() -> new CustomException(INVALID_SEASON)), ResponseProductSeason.class));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllProductSort() {
        List<ProductSort> productSortList = iProductSortRepository.findAll();
        List<ResponseProductSort> responseProductSorts = new ArrayList<>();
        productSortList.forEach(
                productSort -> {
                    responseProductSorts.add(
                            modelMapper.map(productSort, ResponseProductSort.class)
                    );
                });
        Message message = new Message();
        message.setMessage("정렬 전체 조회 성공!");
        message.setData(responseProductSorts);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
