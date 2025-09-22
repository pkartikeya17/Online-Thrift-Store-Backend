//package com.ecommerceFE.product_catalog.repository;
//
//import com.ecommerceFE.product_catalog.model.Category;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CategoryRepository extends JpaRepository<Category , Long> {
//
//}
//package com.ecommerce.project.service;
//
//import com.ecommerce.project.model.Category;
//import com.ecommerce.project.repositories.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@Service
//public class CategoryServiceImplementation implements CategoryService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Override
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    @Override
//    public Category getCategoryById(Long categoryId) {
//        return categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + categoryId));
//    }
//
//    @Override
//    public Category createCategory(Category category) {
//        // Validate category name
//        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name cannot be empty");
//        }
//
//        // Check if category already exists
//        if (categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
//        }
//
//        // Set ID to null to ensure database generates it
//        category.setCategoryId(null);
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public String deleteCategory(Long categoryId) {
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + categoryId));
//
//        // Check if category has products
//        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete category that contains products");
//        }
//
//        categoryRepository.delete(category);
//        return "Category with ID " + categoryId + " deleted successfully";
//    }
//
//    @Override
//    public Category updateCategory(Category category, Long categoryId) {
//        Category existingCategory = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + categoryId));
//
//        // Validate and update category name if provided
//        if (category.getCategoryName() != null && !category.getCategoryName().trim().isEmpty()) {
//            // Check if another category with this name exists
//            categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName())
//                    .ifPresent(existingCat -> {
//                        if (!existingCat.getCategoryId().equals(categoryId)) {
//                            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
//                        }
//                    });
//            existingCategory.setCategoryName(category.getCategoryName());
//        }
//
//        if (category.getDescription() != null) {
//            existingCategory.setDescription(category.getDescription());
//        }
//
//        return categoryRepository.save(existingCategory);
//    }
//}
package com.ecommerceFE.product_catalog.repository;

import com.ecommerceFE.product_catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNameIgnoreCase(String categoryName);
    boolean existsByCategoryName(String categoryName);
}