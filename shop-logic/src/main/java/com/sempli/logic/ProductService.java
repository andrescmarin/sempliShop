package com.sempli.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import java.util.List;
 
import com.sempli.logic.Product;
import com.sempli.logic.Product;

public interface ProductService {
     
    Product findById(long id);
     
    Product findByName(String name);
     
    void saveProduct(Product product);
     
    void updateProduct(Product product);
     
    void deleteProductById(long id);
 
    List<Product> findAllProducts();
     
    void deleteAllProducts();
     
    boolean isProductExist(Product product);
     
}
