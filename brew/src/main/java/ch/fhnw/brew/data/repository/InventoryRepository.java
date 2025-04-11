package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByInventoryCategoryName(String name);
}
