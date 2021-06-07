package com.company.controller;

import com.company.model.Category;
import com.company.model.Product;
import com.company.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
@Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Product> products = productService.findAll();
        if (products.size() == 0) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product=productService.findById(id);
        if(!productService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> editProduct = productService.findById(id);
        if (!editProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        product.setId(editProduct.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> editDelete(@PathVariable Long id) {
        Optional<Product> deleteProduct = productService.findById(id);
        if (!deleteProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
