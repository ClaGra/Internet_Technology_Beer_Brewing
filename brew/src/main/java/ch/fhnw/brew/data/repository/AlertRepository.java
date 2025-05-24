package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    boolean existsByAlertTriggerStartingWith(String prefix);
    void deleteByAlertTriggerStartingWith(String prefix);
}
