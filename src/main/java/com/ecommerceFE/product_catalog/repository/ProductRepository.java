//package com.ecommerceFE.product_catalog.repository;
//
//import com.ecommerceFE.product_catalog.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product , Long> {
//   List<Product> findByCategoryId(Long categoryID);
//
//}
//package com.ecommerce.project.repositories;
//
//import com.ecommerce.project.model.Category;
//import com.ecommerce.project.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ProductRepository extends JpaRepository<Product, Long> {
//
//   List<Product> findByCategoryOrderByPriceAsc(Category category);
//
//   List<Product> findByProductNameContainingIgnoreCase(String productName);
//
//   @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.description LIKE %:keyword%")
//   List<Product> searchByKeyword(@Param("keyword") String keyword);
//
//   List<Product> findByQuantityGreaterThan(Integer quantity);
//
//   @Query("SELECT p FROM Product p WHERE p.specialPrice BETWEEN :minPrice AND :maxPrice")
//   List<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
//}
package com.ecommerceFE.product_catalog.repository;

import com.ecommerceFE.product_catalog.model.Category;
import com.ecommerceFE.product_catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findByCategoryOrderByPriceAsc(Category category);
   List<Product> findByProductNameContainingIgnoreCase(String productName);
}