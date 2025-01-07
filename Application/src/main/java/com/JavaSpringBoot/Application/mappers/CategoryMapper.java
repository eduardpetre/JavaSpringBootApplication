package com.JavaSpringBoot.Application.mappers;

import com.JavaSpringBoot.Application.dto.Category.DtoCategoryCreate;
import com.JavaSpringBoot.Application.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category createCategory(DtoCategoryCreate request) {
        Category category = new Category();
        category.setTag(request.getTag());
        return category;
    }
}
