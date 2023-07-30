package com.springdatajpa.springdatajpacourse.repository;

import com.springdatajpa.springdatajpacourse.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //Create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        // Save product
        Product savedObject = productRepository.save(product);

        // Display product information
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());
    }

    @Test
    void updateUsingSaveMethod(){
        // Find or retrieve an entity by id
        Long id= 1L;
        Product product = productRepository.findById(id).get();

        // Update entity information
        product.setName("Updated product 1");
        product.setDescription("Update product 1 description");

        // save update entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product2.png");

        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 2 description");
        product3.setSku("100ABCE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product2.png");

        Product product4 = new Product();
        product4.setName("product 4");
        product4.setDescription("product 4 description");
        product4.setSku("100ABCefm");
        product4.setPrice(new BigDecimal(400));
        product4.setActive(true);
        product4.setImageUrl("product4.png");

        productRepository.saveAll(List.of(product, product3, product4));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        products.forEach((p) ->{
            System.out.println(p.getName());
        });
    }

    @Test
    void deleteByIdMethod(){
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod(){
        // Find an entity by ID
        Long id = 2L;
        Product product = productRepository.findById(id).get();
        //delete(entity)
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        //Delete all method
        //productRepository.deleteAll();

        Product product = productRepository.findById(5L).get();
        Product product1 = productRepository.findById(6L).get();
        productRepository.deleteAll(List.of(product, product1));
    }

    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println(count);
    }
}