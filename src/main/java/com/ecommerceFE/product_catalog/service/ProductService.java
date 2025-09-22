package com.ecommerceFE.product_catalog.service;

import com.ecommerceFE.product_catalog.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(int page, int size, String sortBy, String sortOrder);

    List<Product> getProductsByCategory(Long categoryId);

    Product getProductById(Long productId);

    Product addProduct(Long categoryId, Product product);

    Product updateProduct(Long productId, Product product);

    String deleteProduct(Long productId);
}