package com.shop.products.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.shop.products.service.repositories.ProductRepository;
import com.shop.products.service.entities.Product;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }


}
