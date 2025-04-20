package com.akka.project.akka_order_processing.messages;

public class OrderMessage {
    public final String orderId;
    public final double amount;

    public OrderMessage(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}