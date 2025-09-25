package com.example.finances.dto;

import com.example.finances.enuns.TransactionType;


import java.time.LocalDateTime;

public class TransactionDto {
    private Long transationId;
    private String description;
    private Double value;
    private LocalDateTime date;
    private TransactionType type;

    private Long userId;
    private Long categoryId;

    public TransactionDto() {

    }

    public Long getId() {
        return transationId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setId(Long id) {
        this.transationId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long user) {
        this.userId = user;
    }
}
