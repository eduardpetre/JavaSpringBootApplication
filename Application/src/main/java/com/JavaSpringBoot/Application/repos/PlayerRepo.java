package com.JavaSpringBoot.Application.repos;

import com.JavaSpringBoot.Application.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    Optional<Player> findByNickname(String nickname);
}
