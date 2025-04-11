package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.Bottling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottlingRepository extends JpaRepository<Bottling, Integer> {
}
