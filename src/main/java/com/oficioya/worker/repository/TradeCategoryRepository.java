package com.oficioya.worker.repository;

import com.oficioya.worker.entity.TradeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TradeCategoryRepository extends JpaRepository<TradeCategory, Long> {

    Optional<TradeCategory> findByNameIgnoreCase(String name);
}