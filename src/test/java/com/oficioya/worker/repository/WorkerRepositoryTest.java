package com.oficioya.worker.repository;

import com.oficioya.worker.entity.Worker;
import com.oficioya.worker.model.WorkerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    private Worker newWorker(Long userId, boolean availableNow) {
        Worker worker = new Worker();
        worker.setUserId(userId);
        worker.setStatus(WorkerStatus.ACTIVE);
        worker.setAvailableNow(availableNow);
        return worker;
    }

    @Test
    @DisplayName("Debe guardar un worker y generar su id")
    void shouldSaveWorker() {
        Worker saved = workerRepository.save(newWorker(1L, false));

        assertNotNull(saved.getId());
        assertEquals(1L, saved.getUserId());
    }

    @Test
    @DisplayName("Debe buscar un worker por userId")
    void shouldFindByUserId() {
        workerRepository.save(newWorker(10L, false));

        assertTrue(workerRepository.findByUserId(10L).isPresent());
        assertTrue(workerRepository.findByUserId(99L).isEmpty());
    }

    @Test
    @DisplayName("Debe listar solo los workers disponibles ahora")
    void shouldFindAvailableNow() {
        workerRepository.save(newWorker(1L, true));
        workerRepository.save(newWorker(2L, false));

        List<Worker> result = workerRepository.findByAvailableNowTrue();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getUserId());
    }

    @Test
    @DisplayName("Debe actualizar el estado de un worker")
    void shouldUpdateWorker() {
        Worker saved = workerRepository.save(newWorker(1L, false));

        saved.setStatus(WorkerStatus.PAUSED);
        workerRepository.save(saved);

        assertEquals(WorkerStatus.PAUSED, workerRepository.findById(saved.getId()).get().getStatus());
    }

    @Test
    @DisplayName("Debe eliminar un worker")
    void shouldDeleteWorker() {
        Worker saved = workerRepository.save(newWorker(1L, false));

        workerRepository.deleteById(saved.getId());

        assertTrue(workerRepository.findById(saved.getId()).isEmpty());
    }
}