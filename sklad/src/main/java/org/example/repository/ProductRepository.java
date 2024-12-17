package org.example.repository;

import org.example.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Найти товары, которые испортятся в ближайшие N дней
    List<Product> findByManufactureDatePlusDaysLessThan(LocalDate date);

}
