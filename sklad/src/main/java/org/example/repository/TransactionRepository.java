package org.example.repository;

import org.example.models.TranType;
import org.example.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetweenAndType(LocalDate start, LocalDate end, TranType type);
}
