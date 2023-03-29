package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.service.IProductOptionService;
import spaland.products.vo.RequestProductOption;
import spaland.products.vo.ResponseProductOption;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/option")
@RequiredArgsConstructor
public class ProductOptionController {

    private final IProductOptionService iProductOptionService;

    @PostMapping
    public void addOption(@RequestBody RequestProductOption requestProductOption) {
        iProductOptionService.addProductOption(requestProductOption);
    }

    @GetMapping("/{productOptionId}")
    public ResponseProductOption getProductOption(@PathVariable Integer productOptionId) {
        return iProductOptionService.getProductOption(productOptionId);
    }

    @GetMapping("/all")
    public List<ResponseProductOption> getAll() {
        return iProductOptionService.getAllProductOption();
    }
}
