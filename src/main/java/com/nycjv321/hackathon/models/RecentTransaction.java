package com.nycjv321.hackathon.models;

import java.math.BigDecimal;
import java.util.Objects;

public class RecentTransaction {

    private String status, date, description, category;
    private BigDecimal amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecentTransaction that = (RecentTransaction) o;
        return status.equals(that.status) &&
                date.equals(that.date) &&
                description.equals(that.description) &&
                category.equals(that.category) &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, description, category, amount);
    }

    public RecentTransaction(String status, String date, String description, String category, BigDecimal amount) {
        this.status = status;
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RecentTransaction{" +
                "status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}

