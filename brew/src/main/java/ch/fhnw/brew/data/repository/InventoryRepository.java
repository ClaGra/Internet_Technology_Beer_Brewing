package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByInventoryCategoryName(String name);
    List<Inventory> findByBatchNr(Integer batchNr);
}
