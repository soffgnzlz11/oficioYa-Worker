package com.oficioya.worker.model;

import jakarta.validation.constraints.NotBlank;

public class TradeCategoryModel {
    private Long id;
    @NotBlank
    private String name;
    private String detail;

    public TradeCategoryModel() {
    }

    public TradeCategoryModel(Long id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}