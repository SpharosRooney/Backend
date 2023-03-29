package spaland.history.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spaland.CurrentDateTime;
import spaland.history.model.History;
import spaland.history.repository.IHistoryRepository;
import spaland.history.vo.RequestHistory;
import spaland.products.model.Product;
import spaland.products.repository.IProductRepository;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.repository.IUserShippingAddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements IHistoryService{

    private final IHistoryRepository iHistoryRepository;
    private final IProductRepository iProductRepository;
    private final IUserShippingAddressRepository iUserShippingAddressRepository;
    CurrentDateTime cdt = new CurrentDateTime();
    @Override
    public void addHistory(RequestHistory requestHistory) {
        ModelMapper modelMapper = new ModelMapper();
//        History history = modelMapper.map(requestHistory, History.class);
        Product prod = iProductRepository.findById(requestHistory.getProductId()).get();
        UserShippingAddress usersa = iUserShippingAddressRepository.findById(requestHistory.getUserShippingAddress()).get();
        History history = History.builder()
                .product(prod)
                .productName(prod.getName())
                .price(prod.getPrice())
                .amount(requestHistory.getAmount())
                .total(prod.getPrice()*requestHistory.getAmount())
//                .titleImg(prod.getTitle())

                .userShippingAddress(usersa)
                .zipCode(usersa.getZipCode())
                .address(usersa.getAddress())
                .detailAddress(usersa.getDetailAddress())
                .shippingPhone(usersa.getShippingPhone())

                .purchasePath(requestHistory.getPurchasePath())
                .giftText(requestHistory.getGiftText())
                .currentState(0L)
                .historyNum("" + cdt.year + cdt.monthValue + cdt.dayOfMonth + cdt.hour + cdt.minute + cdt.second)
                .paymentType(requestHistory.getPaymentType())
                .build();

        iHistoryRepository.save(history);
    }

    @Override
    public History getHistory(Integer historyId) {
        return iHistoryRepository.findById(historyId).get();
    }

    @Override
    public List<History> findAll() {
        return iHistoryRepository.findAll();
    }
}
