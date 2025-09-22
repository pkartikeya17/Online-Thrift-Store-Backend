//package com.ecommerceFE.product_catalog.controller;
//
//import com.ecommerceFE.product_catalog.model.Product;
//import com.ecommerceFE.product_catalog.service.ProductService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/products")
//@CrossOrigin(origins = "https://localhost:5137")
//public class ProductController {
//
//
//    private final ProductService productService;
//
//    public ProductController(ProductService productService){
//        this.productService = productService;
//    }
//    @GetMapping
//    public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/category/{categoryID}")
//    public List<Product> getAllProductsByCategory( @PathVariable  Long categoryID){
//        return productService.getProductByCategory(categoryID);
//    }
//}
package com.ecommerceFE.product_catalog.controller;

import com.ecommerceFE.product_catalog.model.Product;
import com.ecommerceFE.product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/public/products")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Product> products = productService.getAllProducts(page, size, sortBy, sortOrder);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/public/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        try {
            Product savedProduct = productService.addProduct(categoryId, product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(productId, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            String message = productService.deleteProduct(productId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }
}