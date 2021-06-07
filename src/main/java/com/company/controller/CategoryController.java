package com.company.controller;

import com.company.model.Category;
import com.company.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.size() == 0) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> category=categoryService.findById(id);
        if(!categoryService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> editCategory = categoryService.findById(id);
        if (!editCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        category.setId(editCategory.get().getId());
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id) {
        Optional<Category> editCategory = categoryService.findById(id);
        if (!editCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}