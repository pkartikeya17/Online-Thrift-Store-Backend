
package com.ecommerceFE.product_catalog.config;

import com.ecommerceFE.product_catalog.model.Category;
import com.ecommerceFE.product_catalog.model.Product;
import com.ecommerceFE.product_catalog.repository.CategoryRepository;
import com.ecommerceFE.product_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component // Uncommented this to enable data seeding
public class DataSeeder implements CommandLineRunner {

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private ProductRepository productRepository;

        @Override
        public void run(String... args) throws Exception {

                // Check if data already exists
                if (categoryRepository.count() > 0) {
                        System.out.println("Data already exists, skipping seeding...");
                        return;
                }

                // Create Categories
                Category electronics = new Category();
                electronics.setCategoryName("Electronics");
                electronics.setDescription("Electronic devices and gadgets");

                Category clothing = new Category();
                clothing.setCategoryName("Clothing");
                clothing.setDescription("Fashion and apparel");

                Category home = new Category();
                home.setCategoryName("Home & Kitchen");
                home.setDescription("Home and kitchen essentials");

                // Save categories
                categoryRepository.saveAll(Arrays.asList(electronics, clothing, home));

                // Create Products
                Product phone = new Product();
                phone.setProductName("Smartphone");
                phone.setDescription("Latest model smartphone with amazing features");
                phone.setImage("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=600&h=400&fit=crop");
                phone.setPrice(new BigDecimal("699.99"));
                phone.setQuantity(50);
                phone.setDiscount(new BigDecimal("10.0"));
                phone.setSpecialPrice(new BigDecimal("629.99"));
                phone.setCategory(electronics);

                Product laptop = new Product();
                laptop.setProductName("Laptop");
                laptop.setDescription("High-performance laptop for work and play");
                laptop.setImage("https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=600&h=400&fit=crop");
                laptop.setPrice(new BigDecimal("999.99"));
                laptop.setQuantity(30);
                laptop.setDiscount(new BigDecimal("15.0"));
                laptop.setSpecialPrice(new BigDecimal("849.99"));
                laptop.setCategory(electronics);

                Product jacket = new Product();
                jacket.setProductName("Winter Jacket");
                jacket.setDescription("Warm and cozy jacket for winters");
                jacket.setImage("https://images.unsplash.com/photo-1551028719-00167b16eac5?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8TGVhdGhlciUyMEphY2tldHxlbnwwfHwwfHx8MA%3D%3D");
                jacket.setPrice(new BigDecimal("129.99"));
                jacket.setQuantity(100);
                jacket.setDiscount(new BigDecimal("20.0"));
                jacket.setSpecialPrice(new BigDecimal("103.99"));
                jacket.setCategory(clothing);

                Product blender = new Product();
                blender.setProductName("Kitchen Blender");
                blender.setDescription("Multi-purpose kitchen blender");
                blender.setImage("https://images.unsplash.com/photo-1570222094114-d054a817e56b?w=600&h=400&fit=crop");
                blender.setPrice(new BigDecimal("79.99"));
                blender.setQuantity(75);
                blender.setDiscount(new BigDecimal("5.0"));
                blender.setSpecialPrice(new BigDecimal("75.99"));
                blender.setCategory(home);

                // Save products
                productRepository.saveAll(Arrays.asList(phone, laptop, jacket, blender));

                System.out.println("Data seeding completed successfully!");
        }
}