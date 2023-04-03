package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spaland.Response.Message;
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
    public ResponseEntity<Message> addCategoryMiddle(@RequestBody RequestCategoryMiddle requestCategoryMiddle) {
        return iCategoryMiddleService.addCategory(requestCategoryMiddle);
 }

    @GetMapping("/{categoryMiddleId}")
    public ResponseEntity<Message> getCategoryMiddle(@PathVariable(value = "categoryMiddleId") Integer categoryMiddleId) {
        return iCategoryMiddleService.getCategoryMiddle(categoryMiddleId);
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return iCategoryMiddleService.getAll();
    }
}
