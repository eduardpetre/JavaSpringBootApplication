package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.models.TournamentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentDetailsRepo extends JpaRepository<TournamentDetails, Integer> {
}
