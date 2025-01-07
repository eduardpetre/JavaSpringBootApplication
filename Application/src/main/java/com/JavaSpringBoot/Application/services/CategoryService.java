package com.JavaSpringBoot.Application.services;

import com.JavaSpringBoot.Application.exceptions.EntityDuplicateError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.models.Category;
import com.JavaSpringBoot.Application.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    private void checkUniqueTag(Category category) {
        Optional<Category> categoryFound = categoryRepo.findByTag(category.getTag());
        if (categoryFound.isPresent()) {
            throw new EntityDuplicateError("Category");
        }
    }

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Object getCategories(String tag) {
        if(tag != null)
            return categoryRepo.findByTag(tag);

        return categoryRepo.findAll();
    }

    public Category createCategory(Category category){
        checkUniqueTag(category);
        return categoryRepo.save(category);
    }

    public Category deleteCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundError("Category")
        );

        categoryRepo.delete(category);
        return category;
    }
}
