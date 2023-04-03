package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.exception.CustomException;
import spaland.products.model.CategoryLarge;
import spaland.products.repository.ICategoryLargeRepository;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.ResponseCategoryLarge;
import spaland.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryLargeServiceImple implements ICategoryLargeService{

    private final ICategoryLargeRepository iCategoryLargeRepository;


    @Override
    public ResponseEntity<Message> addCategory(RequestCategoryLarge requestCategoryLarge) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryLarge categoryLarge = modelMapper.map(requestCategoryLarge, CategoryLarge.class);
        iCategoryLargeRepository.save(categoryLarge);

        Message message = new Message();
        message.setMessage("카테고리 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getCategoryLarge(Integer categoryLargeId) {

        Message message = new Message();
        message.setMessage("카테고리 조회 성공!");
        message.setData(new ModelMapper().map(iCategoryLargeRepository.findById(categoryLargeId).orElseThrow(()-> new CustomException(INVALID_CATEGORY)), ResponseCategoryLarge.class));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
        @Override
    public ResponseEntity<Message> getAll() {
            List<CategoryLarge> categoryLargeList = iCategoryLargeRepository.findAll();
            List<ResponseCategoryLarge> responseCategoryLarges = new ArrayList<>();
            categoryLargeList.forEach(
                    categoryLarge -> {
                        ModelMapper modelMapper = new ModelMapper();
                        responseCategoryLarges.add(
                                modelMapper.map(categoryLarge, ResponseCategoryLarge.class)
                        );
                    });
            Message message = new Message();
            message.setMessage("카테고리 전체 조회 성공!");
            message.setData(responseCategoryLarges);


        return new ResponseEntity<>(message, HttpStatus.OK);
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
