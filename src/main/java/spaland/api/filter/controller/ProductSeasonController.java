package spaland.api.filter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
import spaland.api.filter.service.IProductSeasonService;
import spaland.api.filter.vo.RequestProductSeason;

@Slf4j
@RestController
@RequestMapping("/api/v1/season")
@RequiredArgsConstructor
public class ProductSeasonController {

    private final IProductSeasonService iProductSeasonService;

    @PostMapping
    public ResponseEntity<Message> addSeason(@RequestBody RequestProductSeason requestProductSeason) {
        return iProductSeasonService.addSeason(requestProductSeason);
    }

    @GetMapping("/{productSeasonId}")
    public ResponseEntity<Message> getProductSeason(@PathVariable Integer productSeasonId) {
        return iProductSeasonService.getProductSeason(productSeasonId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return iProductSeasonService.getAllProductSeason();
    }
}
