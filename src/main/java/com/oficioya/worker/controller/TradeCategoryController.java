package com.oficioya.worker.controller;

import com.oficioya.worker.model.TradeCategoryModel;
import com.oficioya.worker.service.TradeCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trade-categories")
@Tag(name = "Trade Categories", description = "Operaciones relacionadas con categorías de oficios")
public class TradeCategoryController {

    private final TradeCategoryService categoryService;

    public TradeCategoryController(TradeCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Listar categorías", description = "Devuelve todas las categorías de oficios")
    public ResponseEntity<List<TradeCategoryModel>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Crear categoría", description = "Registra una nueva categoría de oficios")
    public ResponseEntity<TradeCategoryModel> create(@Valid @RequestBody TradeCategoryModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(model));
    }
}