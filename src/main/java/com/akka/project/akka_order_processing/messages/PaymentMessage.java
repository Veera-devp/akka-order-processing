package com.akka.project.akka_order_processing.messages;

public class PaymentMessage {
    public final String orderId;
    public final double amount;

    public PaymentMessage(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}