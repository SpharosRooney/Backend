package spaland.api.shipping.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.shipping.service.IUserShippingAddressService;
import spaland.api.shipping.vo.RequestAddUserShippingAddress;
import spaland.api.shipping.vo.RequestEditUserShippingAddress;

@Slf4j
@RestController
@RequestMapping("/api/v1/shippingAddress")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final IUserShippingAddressService iUserShippingAddressService;

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
    public ResponseEntity<Message> getAllByUser(Authentication authentication){
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        return iUserShippingAddressService.getAllByUser(userDetails.getUsername());
    }

    @GetMapping("/isUse")
    public ResponseEntity<Message> getAllByUserAndIsUse(Authentication authentication, @RequestParam(value = "isUse") Boolean isUse){
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        return iUserShippingAddressService.getAllByIsUseByUser(userDetails.getUsername(), isUse);
    }
}
