package br.com.bluesburger.orderingsystem.core.ports.out;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public interface PaymentPort {

    Payment processPayment(Payment payment);

}
