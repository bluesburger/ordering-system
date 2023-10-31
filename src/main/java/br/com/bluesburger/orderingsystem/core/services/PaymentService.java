package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.core.ports.out.PaymentPort;
import br.com.bluesburger.orderingsystem.core.services.strategies.payment.PaymentContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentPort{

    private final PaymentContext paymentContext;

    public Payment processPayment(Payment payment) {

        var messagePayment = paymentContext.processPayment(payment);
        payment.updateMessagePayment(messagePayment);

        return payment;
    }
}
