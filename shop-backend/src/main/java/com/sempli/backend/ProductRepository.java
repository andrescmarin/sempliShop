package com.sempli.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sempli.logic.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
