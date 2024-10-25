package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SellerNameAmountDTO {

   private String name;
   private Double amount;

    public SellerNameAmountDTO() {
    }

    public SellerNameAmountDTO(String name, Double total) {
        this.name = name;
        this.amount = total;
    }
    public SellerNameAmountDTO(Sale entity) {
        name = entity.getSeller().getName();
        amount = entity.getAmount();
    }

    public SellerNameAmountDTO(SellerNameAmountDTO entity) {
        name =entity.getName();
        amount = entity.getTotal();
    }

    public Double getTotal() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
