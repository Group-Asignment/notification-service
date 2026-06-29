package com.example.notification_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mirrors the message Order Service publishes. The field names must match the
 * JSON so it deserializes correctly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long customerId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
    private String status;
}
