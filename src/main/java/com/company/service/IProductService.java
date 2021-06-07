package com.company.service;


import com.company.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product category);

    void deleteById(Long id);
}
