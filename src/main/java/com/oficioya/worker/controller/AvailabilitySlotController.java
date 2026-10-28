package com.oficioya.worker.controller;

import com.oficioya.worker.model.AvailabilitySlotModel;
import com.oficioya.worker.service.AvailabilitySlotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workers/{workerId}/availability-slots")
@Tag(name = "Availability Slots", description = "Franjas de disponibilidad del trabajador")
public class AvailabilitySlotController {

    private final AvailabilitySlotService slotService;

    public AvailabilitySlotController(AvailabilitySlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    @Operation(summary = "Listar disponibilidad", description = "Devuelve las franjas de disponibilidad del trabajador")
    public ResponseEntity<List<AvailabilitySlotModel>> findAll(@PathVariable Long workerId) {
        return ResponseEntity.ok(slotService.findAll(workerId));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @PostMapping
    @Operation(summary = "Crear franja de disponibilidad", description = "Registra una nueva franja para el trabajador")
    public ResponseEntity<AvailabilitySlotModel> create(@PathVariable Long workerId,
                                                        @Valid @RequestBody AvailabilitySlotModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(slotService.create(workerId, model));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @PutMapping("/{slotId}")
    @Operation(summary = "Actualizar franja", description = "Modifica una franja de disponibilidad existente")
    public ResponseEntity<AvailabilitySlotModel> update(@PathVariable Long workerId,
                                                        @PathVariable Long slotId,
                                                        @Valid @RequestBody AvailabilitySlotModel model) {
        return ResponseEntity.ok(slotService.update(workerId, slotId, model));
    }

    @PreAuthorize("hasRole('TRABAJADOR') and @workerOwnership.isOwner(#workerId, authentication)")
    @DeleteMapping("/{slotId}")
    @Operation(summary = "Eliminar franja", description = "Elimina una franja de disponibilidad del trabajador")
    public ResponseEntity<Void> delete(@PathVariable Long workerId, @PathVariable Long slotId) {
        slotService.delete(workerId, slotId);
        return ResponseEntity.noContent().build();
    }
}