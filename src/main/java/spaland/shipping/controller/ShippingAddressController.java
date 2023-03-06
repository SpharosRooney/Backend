package spaland.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.shipping.model.ShippingAddress;
import spaland.shipping.service.IShippingAddressService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/shippingAddress")
@RequiredArgsConstructor

public class ShippingAddressController {
    private final IShippingAddressService iShippingAddressService;
    @PostMapping("/add")
    public ShippingAddress addShippingAddress(@RequestBody ShippingAddress shippingAddress) {
        return iShippingAddressService.addShippingAddress(shippingAddress);
    }


    @GetMapping("/get/{shippingAddressId}")
    public ShippingAddress getShippingAddress(@PathVariable Long shippingAddressId){
        return iShippingAddressService.getShippingAddress(shippingAddressId);
    }

    @GetMapping("/get/all")
    public List<ShippingAddress> getAllShippingAddress(){
        return iShippingAddressService.getAllShippingAddress();
    }

    @GetMapping("/get/isUse/{isUse}")
    public List<ShippingAddress> getAllByIsUse(@PathVariable Short isUse){
        return iShippingAddressService.getAllByIsUse(isUse);
    }


}
