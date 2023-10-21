package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentContext {

    private final Map<PaymentMethod, PaymentStrategy> paymentStrategies;

    public PaymentContext(
            PaymentPixStrategy paymentPixStrategy,
            PaymentDebitCardStrategy paymentDebitCardStrategy,
            PaymentCreditCardStrategy paymentCreditCardStrategy
    ) {
        paymentStrategies = new HashMap<>();
        paymentStrategies.put(PaymentMethod.PIX, paymentPixStrategy);
        paymentStrategies.put(PaymentMethod.DEBIT_CARD, paymentDebitCardStrategy);
        paymentStrategies.put(PaymentMethod.CREDIT_CARD, paymentCreditCardStrategy);
    }

    public String processPayment(Payment payment) {
        PaymentMethod paymentMethod = PaymentMethod.valueOf(payment.getPaymentMethod().toUpperCase());
        PaymentStrategy strategy = paymentStrategies.get(paymentMethod);
        if (strategy != null) {
            return strategy.checkoutPayment(payment);
        } else {
            throw new IllegalArgumentException("O metodo de pagamento não e suportado: " + paymentMethod);
        }
    }
}
