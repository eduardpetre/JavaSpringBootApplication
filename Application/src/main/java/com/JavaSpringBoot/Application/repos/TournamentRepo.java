package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Integer> {
    Optional<Tournament> findByTitleAndYear(String title, String year);
    List<Tournament> findByYear(String year);
}
