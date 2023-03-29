package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.service.ICategoryMiddleService;
import spaland.products.vo.RequestCategoryMiddle;
import spaland.products.vo.ResponseCategoryLarge;
import spaland.products.vo.ResponseCategoryMiddle;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoryMiddle")
@RequiredArgsConstructor
@Slf4j
public class CategoryMiddleController {

    private final ICategoryMiddleService iCategoryMiddleService;

    @PostMapping
    public void addCategoryMiddle(@RequestBody RequestCategoryMiddle requestCategoryMiddle) {
        iCategoryMiddleService.addCategory(requestCategoryMiddle);
 }

    @GetMapping("/{categoryMiddleId}")
    public ResponseCategoryMiddle getCategoryMiddle(@PathVariable Integer categoryMiddleId) {
        return iCategoryMiddleService.getCategoryMiddle(categoryMiddleId);
    }

    @GetMapping("/all")
    public List<ResponseCategoryMiddle> getAll() {
        return iCategoryMiddleService.getAll();
    }
}
