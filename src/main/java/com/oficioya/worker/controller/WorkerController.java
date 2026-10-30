package com.oficioya.worker.controller;

import com.oficioya.worker.model.WorkerModel;
import com.oficioya.worker.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/v1/workers")
@Tag(name = "Workers", description = "Operaciones relacionadas con trabajadores")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    // El perfil se crea solo para el usuario del token
    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isSelf(#model.userId, authentication)")
    @PostMapping
    @Operation(summary = "Crear trabajador", description = "Registra un trabajador nuevo con estado Activo")
    public ResponseEntity<WorkerModel> create(@Valid @RequestBody WorkerModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workerService.create(model));
    }

    @GetMapping
    @Operation(summary = "buscar trabajadores", description = "Devuelve todos los trabajadores registrados segun ciertos parametros")
    public ResponseEntity<List<WorkerModel>> search(@RequestParam(required = false) Long tradeId,
                                                    @RequestParam(required = false) Long zoneId,
                                                    @RequestParam(required = false) BigDecimal maxRate,
                                                    @RequestParam(required = false) Boolean availableNow) {
        return ResponseEntity.ok(workerService.search(tradeId, zoneId, maxRate, availableNow));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener trabajador por ID", description = "Devuelve un trabajador según su identificador")
    public ResponseEntity<WorkerModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('TRABAJADOR') and @workerOwnership.isOwner(#id, authentication))")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar trabajador", description = "Modifica la información de un trabajador que no esté Inactivo")
    public ResponseEntity<WorkerModel> update(@PathVariable Long id, @Valid @RequestBody WorkerModel model) {
        return ResponseEntity.ok(workerService.update(id, model));
    }

     @GetMapping("/{id}/zones")
     @Operation(summary = "Listar zonas del trabajador", description = "Devuelve los IDs de las zonas donde trabaja")
    public ResponseEntity<Set<Long>> findZones(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.findZones(id));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#id, authentication)")
    @PutMapping("/{id}/zones/{zoneId}")
    @Operation(summary = "Agregar zona al trabajador", description = "Asocia una zona de cobertura al trabajador")
    public ResponseEntity<Void> addZone(@PathVariable Long id, @PathVariable Long zoneId) {
        workerService.addZone(id, zoneId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#id, authentication)")
    @DeleteMapping("/{id}/zones/{zoneId}")
    @Operation(summary = "Quitar zona del trabajador", description = "Elimina la asociación entre el trabajador y la zona")
    public ResponseEntity<Void> removeZone(@PathVariable Long id, @PathVariable Long zoneId) {
        workerService.removeZone(id, zoneId);
        return ResponseEntity.noContent().build();
    }
}