package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.service.IProductOptionService;
import spaland.products.service.IProductSeasonService;
import spaland.products.vo.RequestProductOption;
import spaland.products.vo.RequestProductSeason;
import spaland.products.vo.ResponseProductOption;
import spaland.products.vo.ResponseProductSeason;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/season")
@RequiredArgsConstructor
public class ProductSeasonController {

    private final IProductSeasonService iProductSeasonService;

    @PostMapping
    public void addSeason(@RequestBody RequestProductSeason requestProductSeason) {
        iProductSeasonService.addSeason(requestProductSeason);
    }

    @GetMapping("/{productSeasonId}")
    public ResponseProductSeason getProductSeason(@PathVariable Integer productSeasonId) {
        return iProductSeasonService.getProductSeason(productSeasonId);
    }

    @GetMapping("/all")
    public List<ResponseProductSeason> getAll() {
        return iProductSeasonService.getAllProductSeason();
    }
}
