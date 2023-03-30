package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spaland.products.service.ICategoryLargeService;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.ResponseCategoryLarge;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoryLarge")
@RequiredArgsConstructor
@Slf4j
public class CategoryLargeController {

    private final ICategoryLargeService iCategoryLargeService;

    @PostMapping
    public void addCategoryLarge(@RequestBody RequestCategoryLarge requestCategoryLarge) {
        iCategoryLargeService.addCategory(requestCategoryLarge);
 }

    @GetMapping("/{categoryLargeId}")
    public ResponseCategoryLarge getCategoryLarge(@PathVariable(value = "categoryLargeId") Integer categoryLargeId) {
        return iCategoryLargeService.getCategoryLarge(categoryLargeId);
    }

    @GetMapping("/all")
    public List<ResponseCategoryLarge> getAll() {
        return iCategoryLargeService.getAll();
    }
}
