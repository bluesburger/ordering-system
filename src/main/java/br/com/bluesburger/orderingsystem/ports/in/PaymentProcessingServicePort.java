package br.com.bluesburger.orderingsystem.ports.in;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public interface PaymentProcessingServicePort {

    Payment processPayment(Payment payment);

}
