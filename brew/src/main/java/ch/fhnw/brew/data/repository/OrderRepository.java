package ch.fhnw.brew.data.repository;

import ch.fhnw.brew.data.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Used to prevent customer deletion if orders exist
    boolean existsByCustomerCustomerID(Integer customerId);

    // Used to check how many units have been ordered from a specific batch
    @Query("SELECT COALESCE(SUM(oi.amount), 0) " +
           "FROM Order o JOIN o.items oi " +
           "WHERE oi.batchNumber = :batchNr")
    Integer getTotalOrderedAmountByBatch(@Param("batchNr") Integer batchNr);
}
