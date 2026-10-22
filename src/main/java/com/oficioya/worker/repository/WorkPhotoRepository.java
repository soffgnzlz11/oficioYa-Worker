package com.oficioya.worker.repository;

import com.oficioya.worker.entity.WorkPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPhotoRepository extends JpaRepository<WorkPhoto, Long> {

    List<WorkPhoto> findByWorkerId(Long workerId);
}