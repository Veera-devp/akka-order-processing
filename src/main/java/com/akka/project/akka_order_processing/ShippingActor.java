package com.akka.project.akka_order_processing;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import com.akka.project.akka_order_processing.messages.ShippingMessage;

public class ShippingActor extends AbstractBehavior<ShippingMessage> {

    public static Behavior<ShippingMessage> create() {
        return Behaviors.setup(ShippingActor::new);
    }

    private ShippingActor(ActorContext<ShippingMessage> context) {
        super(context);
    }

    @Override
    public Receive<ShippingMessage> createReceive() {
        return newReceiveBuilder()
            .onMessage(ShippingMessage.class, this::onShippingReceived)
            .build();
    }

    private Behavior<ShippingMessage> onShippingReceived(ShippingMessage msg) {
        getContext().getLog().info("🚚 Shipping Order: {}", msg.orderId);
        return this;
    }
}
