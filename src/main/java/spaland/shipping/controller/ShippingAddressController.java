package spaland.shipping.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
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

    //@todo  RequestAddUserShippingAddress 필드 값으로 isUse가 필요하나요?
    @PostMapping
    public ResponseEntity<Message> addUserShippingAddress(Authentication authentication, @RequestBody RequestAddUserShippingAddress requestAddUserShippingAddress){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("add shipping address : {}", requestAddUserShippingAddress);
        return iUserShippingAddressService.addShippingAddressByUser(requestAddUserShippingAddress,userDetails.getUsername());
    }

    @PutMapping
    public ResponseEntity<Message> editUserShippingAddress(Authentication authentication, @RequestBody RequestEditUserShippingAddress requestEditUserShippingAddress){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("add shipping address : {}", requestEditUserShippingAddress);
        return iUserShippingAddressService.updateShippingAddressByUser(requestEditUserShippingAddress,userDetails.getUsername());
    }

    @GetMapping()
    public ResponseEntity<List<ResponseUserShippingAddress>> getAllByUser(Authentication authentication){
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(
                iUserShippingAddressService.getAllByUser(userDetails.getUsername())
        );
    }

    @GetMapping("/isUse")
    public ResponseEntity<List<ResponseUserShippingAddress>> getAllByUserAndIsUse(Authentication authentication, @RequestParam Boolean isUse){
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(
                iUserShippingAddressService.getAllByIsUseByUser(userDetails.getUsername(), isUse)
        );
    }
}
