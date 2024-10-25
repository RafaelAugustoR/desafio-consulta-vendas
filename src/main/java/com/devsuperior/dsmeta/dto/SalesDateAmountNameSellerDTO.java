package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SalesDateAmountNameSellerDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SalesDateAmountNameSellerDTO() {
    }

    public SalesDateAmountNameSellerDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = sellerName;
    }

    public SalesDateAmountNameSellerDTO(Sale entity) {
        id = entity.getId();
        date = entity.getDate();
        amount = entity.getAmount();
        name = entity.getSeller().getName();
    }

    public SalesDateAmountNameSellerDTO(SalesDateAmountNameSellerDTO entity) {
        id = entity.getId();
        date = entity.getDate();
        amount = entity.getAmount();
        name = entity.getName();

    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
