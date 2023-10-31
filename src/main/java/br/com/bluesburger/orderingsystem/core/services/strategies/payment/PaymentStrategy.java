package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public interface PaymentStrategy {

    String checkoutPayment(Payment payment);
}
