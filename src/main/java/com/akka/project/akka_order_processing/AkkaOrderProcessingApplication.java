package com.akka.project.akka_order_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.ActorRef;

import com.akka.project.akka_order_processing.messages.OrderMessage;
import com.akka.project.akka_order_processing.messages.PaymentMessage;
import com.akka.project.akka_order_processing.messages.ShippingMessage;

@SpringBootApplication
public class AkkaOrderProcessingApplication {
public static void main(String[] args) {
        ActorSystem<ShippingMessage> shippingSystem = ActorSystem.create(ShippingActor.create(), "ShippingSystem");
        ActorSystem<PaymentMessage> paymentSystem = ActorSystem.create(PaymentActor.create(shippingSystem), "PaymentSystem");
        ActorSystem<OrderMessage> orderSystem = ActorSystem.create(OrderActor.create(paymentSystem), "OrderSystem");

        orderSystem.tell(new OrderMessage("BNCH946", 299.99));
        orderSystem.tell(new OrderMessage("ABFG234", 159.75));
    }

}
