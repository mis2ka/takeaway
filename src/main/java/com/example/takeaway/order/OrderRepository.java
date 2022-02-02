package com.example.takeaway.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //dostęp do danych
public interface OrderRepository
        extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByProduct(String product);
    Order findOrderById(Long id);
}
