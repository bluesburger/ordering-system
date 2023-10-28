package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCreditCardStrategy implements PaymentStrategy {

    @Override
    public String checkoutPayment(Payment payment) {
        return "Pagamento via cartao de credito realizado com sucesso!";
    }
}
