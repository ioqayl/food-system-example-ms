package org.food.truck.ordering.domain.service.message.listener;

import org.food.truck.ordering.domain.dto.message.PaymentResponse;
import org.food.truck.ordering.domain.ports.input.message.listener.PaymentResponseMessageListener;

public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
