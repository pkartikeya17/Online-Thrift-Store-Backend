
package com.ecommerceFE.product_catalog.service;

import com.ecommerceFE.product_catalog.model.Category;
import com.ecommerceFE.product_catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public Category createCategory(Category category) {
        // Validation
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name cannot be empty");
        }

        // Check if category already exists
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
        }

        category.setCategoryId(null); // Let database generate ID
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        // Update fields if provided
        if (category.getCategoryName() != null && !category.getCategoryName().trim().isEmpty()) {
            // Check if new name already exists (excluding current category)
            boolean nameExists = categoryRepository.existsByCategoryName(category.getCategoryName());
            boolean isDifferentName = !existingCategory.getCategoryName().equals(category.getCategoryName());

            if (nameExists && isDifferentName) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
            }
            existingCategory.setCategoryName(category.getCategoryName());
        }

        if (category.getDescription() != null) {
            existingCategory.setDescription(category.getDescription());
        }

        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        // Check if category has products
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot delete category that contains products. Please delete all products first.");
        }

        categoryRepository.delete(category);
        return "Category with ID " + categoryId + " deleted successfully";
    }

    /**
     * @param category
     * @param categoryId
     * @return
     */
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return null;
    }


}