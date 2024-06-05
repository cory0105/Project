package PTR.PTR.service;

import PTR.PTR.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
