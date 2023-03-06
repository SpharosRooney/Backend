package spaland.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spaland.shipping.model.UserShippingAddressList;
import spaland.shipping.service.IUserShippingAddressListService;
import spaland.shipping.vo.RequestUserShippingAddressList;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user-shippingAddress")
@RequiredArgsConstructor
public class UserShippingAddressListController {
    private final IUserShippingAddressListService iUserShippingAddressListService;

    @PostMapping("/add")
    public UserShippingAddressList addUserShippingAddressList(@RequestBody RequestUserShippingAddressList requestUserShippingAddressList){
        return iUserShippingAddressListService.addUserShippingAddressList(requestUserShippingAddressList);
    }

    @GetMapping("/get/userId/{userId}")
    public List<UserShippingAddressList> getAllByUserId(@PathVariable Long userId){
        return iUserShippingAddressListService.getAllbyUserId(userId);
    }

}
