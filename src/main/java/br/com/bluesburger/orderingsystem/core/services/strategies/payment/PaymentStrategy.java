package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public interface PaymentStrategy {

    Payment checkoutPayment(Payment payment);
}
