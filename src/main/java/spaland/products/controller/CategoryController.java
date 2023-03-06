package spaland.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import spaland.products.model.Category;
import spaland.products.model.CategoryLarge;
import spaland.products.model.CategoryMiddle;
import spaland.products.model.CategorySmall;
import spaland.products.service.ICategoryLargeService;
import spaland.products.service.ICategoryMiddleService;
import spaland.products.service.ICategoryService;
import spaland.products.service.ICategorySmallService;
import spaland.products.vo.RequestCategory;
import spaland.products.vo.RequestCategoryLarge;
import spaland.products.vo.RequestCategoryMiddle;
import spaland.products.vo.RequestCategorySmall;


import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    private final ICategoryLargeService iCategoryLargeService;
    private final ICategoryMiddleService iCategoryMiddleService;
    private final ICategorySmallService iCategorySmallService;


    @PostMapping("/add")
    public void addCategory(@RequestBody RequestCategory requestCategory){
        iCategoryService.addCategory(requestCategory);
    }

    @GetMapping("/get/{categoryId}")
    public Category getCategory(@PathVariable Integer categoryId){
        return iCategoryService.getCategory(categoryId);
    }

    @GetMapping("/get/all")
    public List<Category> getAllCategory(){
        return iCategoryService.getAll();
    }


    // large category ===============================================================
    @PostMapping("/large/add")
    public void addCategory(@RequestBody RequestCategoryLarge requestCategoryLarge) {
        iCategoryLargeService.addCategory(requestCategoryLarge);
    }

    @GetMapping("/large/get/{categoryId}")
    public CategoryLarge getCategoryLarge(@PathVariable Integer categoryId){
        return iCategoryLargeService.getCategoryLarge(categoryId);
    }

    @GetMapping("/large/get/all")
    public List<CategoryLarge> getAllCategoryLarge(){
        return iCategoryLargeService.getAll();
    }

    // middle category ===============================================================
    @PostMapping("/middle/add")
    public void addCategory(@RequestBody RequestCategoryMiddle requestCategoryMiddle) {
        iCategoryMiddleService.addCategory(requestCategoryMiddle);
    }

    @GetMapping("/middle/get/{categoryId}")
    public CategoryMiddle getCategorymiddle(@PathVariable Integer categoryId){
        return iCategoryMiddleService.getCategoryMiddle(categoryId);
    }

    @GetMapping("/middle/get/all")
    public List<CategoryMiddle> getAllCategoryMiddle(){
        return iCategoryMiddleService.getAll();
    }


    // small category ===============================================================
    @PostMapping("/small/add")
    public void addCategory(@RequestBody RequestCategorySmall requestCategorySmall) {
        iCategorySmallService.addCategory(requestCategorySmall);
    }

    @GetMapping("/small/get/{categoryId}")
    public CategorySmall getCategorySmall(@PathVariable Integer categoryId){
        return iCategorySmallService.getCategorySmall(categoryId);
    }

    @GetMapping("/small/get/all")
    public List<CategorySmall> getAllCategorySmall(){
        return iCategorySmallService.getAll();
    }

}
