package com.shop.products.service.controllers;

import com.shop.products.service.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.products.service.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = productService.getAll();
        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable  int id){
        Product product = productService.getProductById(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

}
