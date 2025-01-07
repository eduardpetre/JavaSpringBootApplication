package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.dto.Category.DtoCategoryCreate;
import com.JavaSpringBoot.Application.mappers.CategoryMapper;
import com.JavaSpringBoot.Application.models.Category;
import com.JavaSpringBoot.Application.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String tag) {
        return ResponseEntity.ok().body(categoryService.getCategories(tag));
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated
                                 @RequestBody DtoCategoryCreate request) {
        Category category = categoryService.createCategory(categoryMapper.createCategory(request));
        return ResponseEntity.created(URI.create("/category/" + category.getCategoryId())).body(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable int categoryId) {
        Category category = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(category);
    }
}
