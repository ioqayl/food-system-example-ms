package org.food.truck.ordering.domain.ports.input.message.listener;

import org.food.truck.ordering.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
