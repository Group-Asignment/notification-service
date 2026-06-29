package com.example.notification_service.listener;

import com.example.notification_service.config.RabbitMQConfig;
import com.example.notification_service.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Listens to the order queue. Whenever Order Service publishes an order event,
 * this method runs automatically and logs a (mock) notification.
 */
@Component
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);

    // @RabbitListener makes this method run for every message on the queue.
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleOrderEvent(OrderEvent event) {
        log.info("=================== NEW ORDER NOTIFICATION ===================");
        log.info("Dear customer #{}, your order #{} has been {}.",
                event.getCustomerId(), event.getOrderId(), event.getStatus());
        log.info("Details: {} x {} = {}",
                event.getProductName(), event.getQuantity(), event.getTotalPrice());
        log.info("==============================================================");
    }
}
