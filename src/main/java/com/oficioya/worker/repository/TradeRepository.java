package com.oficioya.worker.repository;

import com.oficioya.worker.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByNameContainingIgnoreCase(String name);

    List<Trade> findByActiveTrue();
}