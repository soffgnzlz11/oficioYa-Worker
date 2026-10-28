package com.oficioya.worker.controller;

import com.oficioya.worker.model.WorkerTradeModel;
import com.oficioya.worker.service.WorkerTradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/workers/{workerId}")
@Tag(name = "Worker Trades", description = "Oficios principal y secundarios de un trabajador")
public class WorkerTradeController {

    private final WorkerTradeService workerTradeService;

    public WorkerTradeController(WorkerTradeService workerTradeService) {
        this.workerTradeService = workerTradeService;
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @PostMapping("/trades")
    @Operation(summary = "Agregar oficio secundario", description = "Asocia un oficio secundario al trabajador")
    public ResponseEntity<WorkerTradeModel> addSecondary(@PathVariable Long workerId,
                                                         @Valid @RequestBody WorkerTradeModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workerTradeService.addSecondary(workerId, model));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @PutMapping("/trades/{workerTradeId}")
    @Operation(summary = "Actualizar oficio del trabajador", description = "Modifica la información de un oficio asociado al trabajador")
    public ResponseEntity<WorkerTradeModel> update(@PathVariable Long workerId,
                                                   @PathVariable Long workerTradeId,
                                                   @Valid @RequestBody WorkerTradeModel model) {
        return ResponseEntity.ok(workerTradeService.update(workerId, workerTradeId, model));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @DeleteMapping("/trades/{workerTradeId}")
    @Operation(summary = "Eliminar oficio secundario", description = "Quita un oficio secundario del trabajador")
    public ResponseEntity<Void> deleteSecondary(@PathVariable Long workerId,
                                                @PathVariable Long workerTradeId) {
        workerTradeService.deleteSecondary(workerId, workerTradeId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @PutMapping("/primary-trade/{workerTradeId}")
    @Operation(summary = "Cambiar oficio principal", description = "Define cuál de los oficios del trabajador es el principal")
    public ResponseEntity<WorkerTradeModel> changePrimary(@PathVariable Long workerId,
                                                          @PathVariable Long workerTradeId) {
        return ResponseEntity.ok(workerTradeService.changePrimary(workerId, workerTradeId));
    }
}