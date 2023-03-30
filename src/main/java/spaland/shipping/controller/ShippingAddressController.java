package spaland.shipping.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.shipping.model.UserShippingAddress;
import spaland.shipping.service.IUserShippingAddressService;
import spaland.shipping.vo.RequestAddUserShippingAddress;
import spaland.shipping.vo.RequestEditUserShippingAddress;
import spaland.shipping.vo.ResponseUserShippingAddress;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/shippingAddress")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final IUserShippingAddressService iUserShippingAddressService;

    @PostMapping
    public void addUserShippingAddress( @RequestBody RequestAddUserShippingAddress requestAddUserShippingAddress){
        log.info("add shipping address : {}", requestAddUserShippingAddress);
        iUserShippingAddressService.addShippingAddressByUser(requestAddUserShippingAddress);
    }

    @PutMapping
    public void editUserShippingAddress(@RequestBody RequestEditUserShippingAddress requestEditUserShippingAddress){
        log.info("add shipping address : {}", requestEditUserShippingAddress);
        iUserShippingAddressService.updateShippingAddressByUser(requestEditUserShippingAddress);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseUserShippingAddress>> getAllByUser(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(
                iUserShippingAddressService.getAllByUser(userId)
        );
    }

    @GetMapping("/isUse")
    public ResponseEntity<List<ResponseUserShippingAddress>> getAllByUserAndIsUse(@RequestParam Long userId, Boolean isUse){
        return ResponseEntity.ok(
                iUserShippingAddressService.getAllByIsUseByUser(userId, isUse)
        );
    }
}
