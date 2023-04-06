package spaland.api.filter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.filter.service.IProductSortService;
import spaland.api.filter.vo.RequestProductSeason;
import spaland.api.filter.vo.RequestProductSort;

@RestController
@RequestMapping("/api/v1/sort")
@RequiredArgsConstructor
public class ProductSortController {

    private final IProductSortService iProductSortService;

    @PostMapping
    public ResponseEntity<Message> addProductSort(@RequestBody RequestProductSort requestProductSort) {
        return iProductSortService.addProductSort(requestProductSort);
    }

    @GetMapping("/{productSortId}")
    public ResponseEntity<Message> getProductSort(@PathVariable(value = "productSortId") Integer productSortId) {
        return iProductSortService.getProductSort(productSortId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return iProductSortService.getAllProductSort();
    }

}
