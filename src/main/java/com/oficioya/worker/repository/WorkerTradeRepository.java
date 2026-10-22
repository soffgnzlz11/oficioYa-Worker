package com.oficioya.worker.repository;

import com.oficioya.worker.entity.WorkerTrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerTradeRepository extends JpaRepository<WorkerTrade, Long> {

    List<WorkerTrade> findByWorkerId(Long workerId);

    Optional<WorkerTrade> findByWorkerIdAndPrimaryTrue(Long workerId);
}