package com.oficioya.worker.controller;

import com.oficioya.worker.model.TradeModel;
import com.oficioya.worker.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trades")
@Tag(name = "Trades", description = "Operaciones relacionadas con oficios")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    @Operation(summary = "Listar oficios", description = "Devuelve los oficios; permite filtrar por activos con el parámetro active")
    public ResponseEntity<List<TradeModel>> findAll(@RequestParam(required = false) Boolean active) {
        return ResponseEntity.ok(tradeService.findAll(active));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener oficio por ID", description = "Devuelve un oficio según su identificador")
    public ResponseEntity<TradeModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tradeService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear oficio", description = "Registra un nuevo oficio")
    public ResponseEntity<TradeModel> create(@Valid @RequestBody TradeModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tradeService.create(model));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar oficio", description = "Modifica los datos de un oficio")
    public ResponseEntity<TradeModel> update(@PathVariable Long id, @Valid @RequestBody TradeModel model) {
        return ResponseEntity.ok(tradeService.update(id, model));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desactivar oficio", description = "Inactiva el oficio; no se elimina físicamente")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        tradeService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}