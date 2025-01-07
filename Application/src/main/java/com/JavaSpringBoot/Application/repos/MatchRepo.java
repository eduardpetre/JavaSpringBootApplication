package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.enums.SportTypes;
import com.JavaSpringBoot.Application.models.Category;
import com.JavaSpringBoot.Application.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
    List<Match> findBySportType(SportTypes s);
    List<Match> findByCategoryListIsContaining(Category c);
}
