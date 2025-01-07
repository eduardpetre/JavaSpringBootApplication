package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByTag(String tag);
}
