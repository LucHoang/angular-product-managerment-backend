package com.company.fomatter;

import com.company.model.Category;
import com.company.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class CategoryFormatter implements Formatter<Category> {
    @Autowired
    ICategoryService categoryService;

    public CategoryFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Optional<Category> category=categoryService.findById(Long.parseLong(text));
        return category.orElse(null);
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}
