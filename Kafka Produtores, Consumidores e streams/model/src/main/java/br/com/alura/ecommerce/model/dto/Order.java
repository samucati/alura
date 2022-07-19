package br.com.alura.ecommerce.model.dto;

import java.math.BigDecimal;

public class Order {
    private final String orderId;
    private final BigDecimal amount;
    private final Email email;

    public Order(String orderId, BigDecimal amount, Email email) {
        this.orderId = orderId;
        this.amount = amount;
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                " orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", email=" + email +
                '}';
    }

    public Email getUserEmail() {
        return email;
    }
}
