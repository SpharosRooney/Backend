package spaland.api.filter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.filter.service.IProductOptionService;
import spaland.api.filter.vo.RequestProductOption;

@Slf4j
@RestController
@RequestMapping("/api/v1/option")
@RequiredArgsConstructor
public class ProductOptionController {

    private final IProductOptionService iProductOptionService;

    @PostMapping
    public ResponseEntity<Message> addOption(@RequestBody RequestProductOption requestProductOption) {
        return iProductOptionService.addProductOption(requestProductOption);
    }

    @GetMapping("/{productOptionId}")
    public ResponseEntity<Message> getProductOption(@PathVariable(value = "productOptionId") Integer productOptionId) {
        return iProductOptionService.getProductOption(productOptionId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return iProductOptionService.getAllProductOption();
    }
}
