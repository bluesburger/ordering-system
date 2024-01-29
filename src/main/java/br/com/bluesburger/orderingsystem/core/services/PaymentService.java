package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.ports.in.PaymentProcessingServicePort;
import br.com.bluesburger.orderingsystem.core.services.strategies.payment.PaymentContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentProcessingServicePort {

    private final PaymentContext paymentContext;

    public Payment processPayment(Payment payment) {
        return paymentContext.processPayment(payment);
    }
}
