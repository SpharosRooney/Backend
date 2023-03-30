package spaland.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spaland.history.dto.ResponseHistoryDTO;
import spaland.history.dto.ResponseHistoryDetailDTO;
import spaland.history.model.History;
import spaland.history.repository.IHistoryRepository;
import spaland.history.vo.RequestHistory;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.repository.IUserShippingAddressRepository;
import spaland.users.model.User;
import spaland.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements IHistoryService{

    private final IHistoryRepository iHistoryRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IUserShippingAddressRepository iUserShippingAddressRepository;

//    @Override
//    public void addHistory(RequestHistory requestHistory) {
//        // 1. userID를 통해 유저를 유저를 식별한다.
//        // 2. 같은 방법으로 상품 배송지를 식별한다.
//        // 3. 모두 만족하면 History에 저장한다.
//
//        iHistoryRepository.save(History.builder()
//                .user(iUserRepository.findById(requestHistory.getUserId()).get())
//                .product(iProductRepository.findById(requestHistory.getProductId()).get())
//                .userShippingAddress(iUserShippingAddressRepository.findById(requestHistory.getUserShippingAddress()).get())
//                .historyNum("asd")
//                .currentState(0L)
//                .amount(requestHistory.getAmount())
//                .paymentType(requestHistory.getPaymentType())
//                .build());
//    }
//
//    @Override
//    public ResponseHistoryDetailDTO getHistory(Integer historyId) {
//        // ResponseHistoryDTO의 필드값에 맞는 레포지토리를 각각 호출하여
//        // 빌더를 통해 넣어준다.
//        History history = iHistoryRepository.findById(historyId).get();
//        Product product = iHistoryRepository.findById(historyId).get().getProduct();
//        UserShippingAddress usa = iHistoryRepository.findById(historyId).get().getUserShippingAddress();
//
//        ResponseHistoryDetailDTO responseHistotyDTO = ResponseHistoryDetailDTO.builder()
//                .productId(product.getId())
//                .productName(product.getName())
//                .productPrice(product.getPrice())
//                .productTitleImg(product.getTitleImg())
//                .zipCode(usa.getZipCode())
//                .address(usa.getAddress())
//                .detailAddress(usa.getDetailAddress())
//                .shippingPhone(usa.getShippingPhone())
//                .currentState(history.getCurrentState())
//                .paymentType(history.getPaymentType())
//                .amount(history.getAmount())
//                .totalPrice(history.getAmount() * history.getAmount())
//                .build();
//
//        return responseHistotyDTO;
//    }
//
//    @Override
//    public List<ResponseHistoryDTO> findAll(Long userId) {
//        // 전체 리스트는
//        List<History> historyList = iHistoryRepository.findAllByUser(iUserRepository.findById(userId).get());
//        List<ResponseHistoryDTO> responseHistoryDTOList = new ArrayList<>();
//
//        historyList.forEach(
//                history -> {
//                    responseHistoryDTOList.add(
//                            ResponseHistoryDTO.builder()
//                            .productId(history.getProduct().getId())
//                            .productName(history.getProduct().getName())
//                            .productTitleImg(history.getProduct().getTitleImg())
//                            .amount(history.getAmount())
//                            .totalPrice(history.getAmount() * history.getAmount())
//                            .build()
//                    );
//                }
//        );
//        return responseHistoryDTOList;
//    }

    @Override
    public void addHistory(RequestHistory requestHistory, String userEmail) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()->new RuntimeException());

        iHistoryRepository.save(History.builder()
                .user(iUserRepository.findById(user.getId()).get())
                .product(iProductRepository.findById(requestHistory.getProductId()).get())
                .userShippingAddress(iUserShippingAddressRepository.findById(requestHistory.getUserShippingAddress()).get())
                .historyNum("asd")
                .currentState(0L)
                .amount(requestHistory.getAmount())
                .paymentType(requestHistory.getPaymentType())
                .build());

    }

    @Override
    public ResponseHistoryDetailDTO getHistory(Integer historyId, String userEmail) {
        History history = iHistoryRepository.findById(historyId).get();
        Product product = iHistoryRepository.findById(historyId).get().getProduct();
        UserShippingAddress usa = iHistoryRepository.findById(historyId).get().getUserShippingAddress();

        ResponseHistoryDetailDTO responseHistotyDTO = ResponseHistoryDetailDTO.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productTitleImg(product.getTitleImg())
                .zipCode(usa.getZipCode())
                .address(usa.getAddress())
                .detailAddress(usa.getDetailAddress())
                .shippingPhone(usa.getShippingPhone())
                .currentState(history.getCurrentState())
                .paymentType(history.getPaymentType())
                .amount(history.getAmount())
                .totalPrice(history.getAmount() * history.getAmount())
                .build();

        return responseHistotyDTO;
    }

    @Override
    public List<ResponseHistoryDTO> findAll(String userEmail) {
        User user = iUserRepository.findByUserId(userEmail).orElseThrow(()->new RuntimeException());
        List<History> historyList = iHistoryRepository.findAllByUser(user);
        List<ResponseHistoryDTO> responseHistoryDTOList = new ArrayList<>();

        historyList.forEach(
                history -> {
                    responseHistoryDTOList.add(
                            ResponseHistoryDTO.builder()
                                    .productId(history.getProduct().getId())
                                    .productName(history.getProduct().getName())
                                    .productTitleImg(history.getProduct().getTitleImg())
                                    .amount(history.getAmount())
                                    .totalPrice(history.getAmount() * history.getAmount())
                                    .build()
                    );
                }
        );
        return responseHistoryDTOList;    }
}
