package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PaymentContext {

    private Map<PaymentMethodEnum, PaymentStrategy> paymentStrategies;
    private final PaymentPixStrategy paymentPixStrategy;
    private final PaymentDebitCardStrategy paymentDebitCardStrategy;
    private final PaymentCreditCardStrategy paymentCreditCardStrategy;

    public Payment processPayment(Payment payment) {
        buildStrategies();

        PaymentMethodEnum paymentMethod = PaymentMethodEnum.valueOf(payment.getPaymentMethod().name().toUpperCase());
        PaymentStrategy strategy = paymentStrategies.get(paymentMethod);

        if (isNull(strategy)) {
            throw new IllegalArgumentException("O metodo de pagamento nao e suportado: " + paymentMethod);
        }
        return strategy.checkoutPayment(payment);
    }

    private void buildStrategies() {
        paymentStrategies = new HashMap<>();

        paymentStrategies.put(PaymentMethodEnum.PIX, paymentPixStrategy);
        paymentStrategies.put(PaymentMethodEnum.DEBIT_CARD, paymentDebitCardStrategy);
        paymentStrategies.put(PaymentMethodEnum.CREDIT_CARD, paymentCreditCardStrategy);
    }
}
