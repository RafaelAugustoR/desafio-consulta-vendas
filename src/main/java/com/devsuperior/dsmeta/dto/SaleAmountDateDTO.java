package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleAmountDateDTO {

    private Double amount;
    private String date;

    public SaleAmountDateDTO() {
    }

    public SaleAmountDateDTO(Double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public SaleAmountDateDTO(Sale entity) {
        amount = entity.getAmount();
        date = entity.getDate().toString();
    }

    public SaleAmountDateDTO(SaleAmountDateDTO entity) {
        amount = entity.getAmount();
        date = entity.getDate().toString();
    }

    public Double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
