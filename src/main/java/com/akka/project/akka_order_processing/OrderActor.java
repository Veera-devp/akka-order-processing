package com.akka.project.akka_order_processing;

import akka.actor.typed.Behavior;
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.*;
import com.akka.project.akka_order_processing.messages.OrderMessage;
import com.akka.project.akka_order_processing.messages.PaymentMessage;

public class OrderActor extends AbstractBehavior<OrderMessage> {

    private final ActorRef<PaymentMessage> paymentActor;

    public static Behavior<OrderMessage> create(ActorRef<PaymentMessage> paymentActor) {
        return Behaviors.setup(ctx -> new OrderActor(ctx, paymentActor));
    }

    private OrderActor(ActorContext<OrderMessage> context, ActorRef<PaymentMessage> paymentActor) {
        super(context);
        this.paymentActor = paymentActor;
    }

    @Override
    public Receive<OrderMessage> createReceive() {
        return newReceiveBuilder()
            .onMessage(OrderMessage.class, this::onOrderReceived)
            .build();
    }

    private Behavior<OrderMessage> onOrderReceived(OrderMessage msg) {
        getContext().getLog().info("📦 Received Order: {} | Amount: ${}", msg.orderId, msg.amount);
        paymentActor.tell(new PaymentMessage(msg.orderId, msg.amount));
        return this;
    }
}
// The OrderActor class is responsible for receiving order messages and forwarding them to the PaymentActor.
// It uses the ActorRef of the PaymentActor to send payment messages.
// The create method sets up the actor with the provided ActorRef for the PaymentActor.
// The onOrderReceived method processes the incoming order message, logs the order details, and sends a payment message to the PaymentActor.
// The OrderActor class is part of the Akka actor system and uses the ActorRef pattern to communicate with other actors.