package PTR.PTR.controller;

import PTR.PTR.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
