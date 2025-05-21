package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.BrewingProtocol;
import ch.fhnw.brew.data.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewingProtocolRepository extends JpaRepository<BrewingProtocol, Integer> {
    boolean existsByRecipe(Recipe recipe); 
}