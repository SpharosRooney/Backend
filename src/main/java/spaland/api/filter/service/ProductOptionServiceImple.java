package spaland.api.filter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.exception.CustomException;
import spaland.api.filter.model.ProductOption;
import spaland.api.filter.repository.IProductOptionRepository;
import spaland.api.filter.vo.RequestProductOption;
import spaland.api.filter.vo.ResponseProductOption;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_OPTION;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductOptionServiceImple implements IProductOptionService{

    private final IProductOptionRepository iProductOptionRepository;

    @Override
    public ResponseEntity<Message> addProductOption(RequestProductOption productOption) {
        iProductOptionRepository.save(new ModelMapper().map(productOption, ProductOption.class));

        Message message = new Message();
        message.setMessage("상품 옵션 등록 성공!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getProductOption(Integer productOptionId) {

        Message message = new Message();
        message.setMessage("상품 옵션 조회 성공!");
        message.setData(new ModelMapper().map(iProductOptionRepository.findById(productOptionId).orElseThrow(() -> new CustomException(INVALID_OPTION)),ResponseProductOption.class));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAllProductOption() {
        List<ProductOption> productOptionList = iProductOptionRepository.findAll();
        List<ResponseProductOption> responseProductOptions = new ArrayList<>();

        productOptionList.forEach(
                productOption -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProductOptions.add(
                            modelMapper.map(productOption,ResponseProductOption.class)
                    );
                });
        Message message = new Message();
        message.setMessage("상품 옵션 전체 조회 성공!");
        message.setData(responseProductOptions);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
