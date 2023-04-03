package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
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
    public ResponseEntity<Message> addCategoryLarge(@RequestBody RequestCategoryLarge requestCategoryLarge) {
        return iCategoryLargeService.addCategory(requestCategoryLarge);
 }

    @GetMapping("/{categoryLargeId}")
    public ResponseEntity<Message> getCategoryLarge(@PathVariable(value = "categoryLargeId") Integer categoryLargeId) {
        return iCategoryLargeService.getCategoryLarge(categoryLargeId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return iCategoryLargeService.getAll();
    }
}
