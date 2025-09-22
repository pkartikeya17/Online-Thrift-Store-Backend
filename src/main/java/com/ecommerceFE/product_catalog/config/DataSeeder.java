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

//@Component
public class DataSeeder implements CommandLineRunner {


        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private ProductRepository productRepository;

        @Override
        public void run(String... args) throws Exception {


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
                phone.setImage("https://placehold.co/600x400");
                phone.setPrice(new BigDecimal("699.99"));
                phone.setQuantity(50);
                phone.setDiscount(new BigDecimal("10.0"));
                phone.setSpecialPrice(new BigDecimal("629.99"));
                phone.setCategory(electronics);

                Product laptop = new Product();
                laptop.setProductName("Laptop");
                laptop.setDescription("High-performance laptop for work and play");
                laptop.setImage("https://placehold.co/600x400");
                laptop.setPrice(new BigDecimal("999.99"));
                laptop.setQuantity(30);
                laptop.setDiscount(new BigDecimal("15.0"));
                laptop.setSpecialPrice(new BigDecimal("849.99"));
                laptop.setCategory(electronics);

                Product jacket = new Product();
                jacket.setProductName("Winter Jacket");
                jacket.setDescription("Warm and cozy jacket for winters");
                jacket.setImage("https://placehold.co/600x400");
                jacket.setPrice(new BigDecimal("129.99"));
                jacket.setQuantity(100);
                jacket.setDiscount(new BigDecimal("20.0"));
                jacket.setSpecialPrice(new BigDecimal("103.99"));
                jacket.setCategory(clothing);

                Product blender = new Product();
                blender.setProductName("Kitchen Blender");
                blender.setDescription("Multi-purpose kitchen blender");
                blender.setImage("https://placehold.co/600x400");
                blender.setPrice(new BigDecimal("79.99"));
                blender.setQuantity(75);
                blender.setDiscount(new BigDecimal("5.0"));
                blender.setSpecialPrice(new BigDecimal("75.99"));
                blender.setCategory(home);

                // Save products
                productRepository.saveAll(Arrays.asList( phone ,  laptop, jacket, blender));

                System.out.println("Data seeding completed successfully!");
        }
}