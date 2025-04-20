package com.akka.project.akka_order_processing;

import akka.actor.typed.Behavior;
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.*;
import com.akka.project.akka_order_processing.messages.PaymentMessage;
import com.akka.project.akka_order_processing.messages.ShippingMessage;

public class PaymentActor extends AbstractBehavior<PaymentMessage> {

    private final ActorRef<ShippingMessage> shippingActor;

    public static Behavior<PaymentMessage> create(ActorRef<ShippingMessage> shippingActor) {
        return Behaviors.setup(ctx -> new PaymentActor(ctx, shippingActor));
    }

    private PaymentActor(ActorContext<PaymentMessage> context, ActorRef<ShippingMessage> shippingActor) {
        super(context);
        this.shippingActor = shippingActor;
    }

    @Override
    public Receive<PaymentMessage> createReceive() {
        return newReceiveBuilder()
            .onMessage(PaymentMessage.class, this::onPaymentReceived)
            .build();
    }

    private Behavior<PaymentMessage> onPaymentReceived(PaymentMessage msg) {
        getContext().getLog().info("💰 Processing Payment: {} | Amount: ${}", msg.orderId, msg.amount);
        shippingActor.tell(new ShippingMessage(msg.orderId));
        return this;
    }
}
