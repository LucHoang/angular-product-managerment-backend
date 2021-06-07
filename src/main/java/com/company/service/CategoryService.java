package com.company.service;

import com.company.model.Category;
import com.company.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository repositoryCategory;

    @Override
    public List<Category> findAll() {
        return (List<Category>) repositoryCategory.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repositoryCategory.findById(id);
    }

    @Override
    public Category save(Category category) {
        return repositoryCategory.save(category);
    }

    @Override
    public void deleteById(Long id) {
        repositoryCategory.deleteById(id);
    }
}
