//package com.ecommerceFE.product_catalog.service;
//
//import com.ecommerceFE.product_catalog.model.Category;
//import com.ecommerceFE.product_catalog.repository.CategoryRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CategoryService {
//    private final CategoryRepository categoryRepository;
//
//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//}
//package com.ecommerceFE.product_catalog.service;
//
//import com.ecommerceFE.product_catalog.model.Category;
//import java.util.List;
//
//public interface CategoryService {
//
//    List<Category> getAllCategories();
//
//    Category getCategoryById(Long categoryId);
//
//    Category createCategory(Category category);
//
//    Category updateCategory(Long categoryId, Category category);
//
//    String deleteCategory(Long categoryId);
//
//    Category updateCategory(Category category, Long categoryId);
//
//}
package com.ecommerceFE.product_catalog.service;

import com.ecommerceFE.product_catalog.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category createCategory(Category category);

    Category updateCategory(Long categoryId, Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}