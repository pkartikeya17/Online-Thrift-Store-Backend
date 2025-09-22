package com.ecommerceFE.product_catalog.service;

import com.ecommerceFE.product_catalog.model.Category;
import com.ecommerceFE.product_catalog.model.Product;
import com.ecommerceFE.product_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerceFE.product_catalog.repository.CategoryRepository;
import com.ecommerceFE.product_catalog.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts(int page, int size, String sortBy, String sortOrder) {
        // Create Sort object based on sortOrder
        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Create Pageable object
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return productRepository.findByCategoryOrderByPriceAsc(category);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        // Validation
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name cannot be empty");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price must be greater than zero");
        }

        if (product.getQuantity() == null || product.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity cannot be negative");
        }

        product.setCategory(category);
        product.setProductId(null); // Let database generate ID

        // Calculate special price if discount is provided
        if (product.getDiscount() != null && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discountAmount = product.getPrice()
                    .multiply(product.getDiscount())
                    .divide(BigDecimal.valueOf(100));
            product.setSpecialPrice(product.getPrice().subtract(discountAmount));
        } else {
            product.setSpecialPrice(product.getPrice());
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        // Update fields if provided
        if (product.getProductName() != null && !product.getProductName().trim().isEmpty()) {
            existingProduct.setProductName(product.getProductName());
        }

        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }

        if (product.getImage() != null) {
            existingProduct.setImage(product.getImage());
        }

        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            existingProduct.setPrice(product.getPrice());
        }

        if (product.getQuantity() != null && product.getQuantity() >= 0) {
            existingProduct.setQuantity(product.getQuantity());
        }

        if (product.getDiscount() != null) {
            existingProduct.setDiscount(product.getDiscount());

            // Recalculate special price
            if (product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal discountAmount = existingProduct.getPrice()
                        .multiply(product.getDiscount())
                        .divide(BigDecimal.valueOf(100));
                existingProduct.setSpecialPrice(existingProduct.getPrice().subtract(discountAmount));
            } else {
                existingProduct.setSpecialPrice(existingProduct.getPrice());
            }
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);
        return "Product with ID " + productId + " deleted successfully";
    }
}