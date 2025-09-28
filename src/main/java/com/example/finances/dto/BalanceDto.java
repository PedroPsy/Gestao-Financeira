package com.example.finances.dto;

public class BalanceDto {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    public BalanceDto(Double totalIncome, Double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = totalIncome - totalExpense;
    }

    // Getters e Setters
    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
