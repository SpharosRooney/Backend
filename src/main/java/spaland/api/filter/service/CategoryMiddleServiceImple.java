package spaland.api.filter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.filter.model.CategoryMiddle;
import spaland.exception.CustomException;
import spaland.api.filter.repository.ICategoryMiddleRepository;
import spaland.api.filter.vo.RequestCategoryMiddle;
import spaland.api.filter.vo.ResponseCategoryMiddle;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.INVALID_CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryMiddleServiceImple implements ICategoryMiddleService{

    private final ICategoryMiddleRepository iCategoryMiddleRepository;


    @Override
    public ResponseEntity<Message> addCategory(RequestCategoryMiddle requestCategoryMiddle) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryMiddle categoryMiddle = modelMapper.map(requestCategoryMiddle, CategoryMiddle.class); // 0번 파라미터가 1번 파라미터로 들어감
        iCategoryMiddleRepository.save(categoryMiddle);

        Message message = new Message();
        message.setMessage("카테고리 등록 성공!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getCategoryMiddle(Integer categoryMiddleId) {

        Message message = new Message();
        message.setMessage("카테고리 조회 성공!");
        message.setData(new ModelMapper().map(iCategoryMiddleRepository.findById(categoryMiddleId).orElseThrow(()-> new CustomException(INVALID_CATEGORY)),ResponseCategoryMiddle.class));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getAll() {
        List<CategoryMiddle> categoryMiddleList =  iCategoryMiddleRepository.findAll();
        List<ResponseCategoryMiddle> responseCategoryMiddles = new ArrayList<>();

        categoryMiddleList.forEach(
                categoryMiddle -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseCategoryMiddles.add(
                            modelMapper.map(categoryMiddle,ResponseCategoryMiddle.class)
                    );
                });
        Message message = new Message();
        message.setMessage("카테고리 전체 조회 성공!");
        message.setData(responseCategoryMiddles);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
//
//    @Override
//    public List<Category> getAllByType(String categoryType) {
//        return ICategoryLargeRepository.findAllByType(categoryType);
//    }


}
