package com.example.notification_service.listener;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.notification_service.event.OrderEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for the listener. We attach a "ListAppender" to capture the log
 * output, call the listener directly with an OrderEvent, then assert that the
 * expected notification text was logged. No RabbitMQ needed.
 */
class OrderEventListenerTest {

    private OrderEventListener listener;
    private ListAppender<ILoggingEvent> logCapture;

    @BeforeEach
    void setUp() {
        listener = new OrderEventListener();

        // Attach an in-memory appender to capture this listener's log messages
        Logger logger = (Logger) LoggerFactory.getLogger(OrderEventListener.class);
        logCapture = new ListAppender<>();
        logCapture.start();
        logger.addAppender(logCapture);
    }

    @Test
    void handleOrderEvent_logsTheNotificationDetails() {
        OrderEvent event = new OrderEvent(1L, 7L, 2L, "Mechanical Keyboard", 3, 13500.0, "CREATED");

        listener.handleOrderEvent(event);

        List<String> logged = logCapture.list.stream()
                .map(ILoggingEvent::getFormattedMessage)
                .toList();

        // The notification should mention the customer, the order, and the product
        assertThat(logged).anyMatch(line -> line.contains("customer #7"));
        assertThat(logged).anyMatch(line -> line.contains("order #1"));
        assertThat(logged).anyMatch(line -> line.contains("Mechanical Keyboard"));
        assertThat(logged).anyMatch(line -> line.contains("CREATED"));
    }
}
