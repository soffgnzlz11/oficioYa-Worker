package com.oficioya.worker.model;

public class TradeModel {
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private TradeCategoryModel category;

    public TradeModel() {
    }

    public TradeModel(Long id, String name, String description, boolean active, TradeCategoryModel category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TradeCategoryModel getCategory() {
        return category;
    }

    public void setCategory(TradeCategoryModel category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}