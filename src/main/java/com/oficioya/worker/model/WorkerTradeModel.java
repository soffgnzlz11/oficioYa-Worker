package com.oficioya.worker.model;

import java.math.BigDecimal;

public class WorkerTradeModel {
    private Long id;
    private BigDecimal approxRate;
    private RateUnit rateUnit;
    private TradeModel trade;

    public WorkerTradeModel() {
    }

    public WorkerTradeModel(Long id, BigDecimal approxRate, RateUnit rateUnit, TradeModel trade) {
        this.id = id;
        this.approxRate = approxRate;
        this.rateUnit = rateUnit;
        this.trade = trade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getApproxRate() {
        return approxRate;
    }

    public void setApproxRate(BigDecimal approxRate) {
        this.approxRate = approxRate;
    }

    public RateUnit getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(RateUnit rateUnit) {
        this.rateUnit = rateUnit;
    }

    public TradeModel getTrade() {
        return trade;
    }

    public void setTrade(TradeModel trade) {
        this.trade = trade;
    }
}