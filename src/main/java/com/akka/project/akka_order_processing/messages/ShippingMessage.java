package com.akka.project.akka_order_processing.messages;

public class ShippingMessage {
    public final String orderId;

    public ShippingMessage(String orderId) {
        this.orderId = orderId;
    }
}