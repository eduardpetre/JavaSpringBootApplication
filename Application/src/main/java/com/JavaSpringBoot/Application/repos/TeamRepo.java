package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
    Optional<Team> findByName(String name);
}
