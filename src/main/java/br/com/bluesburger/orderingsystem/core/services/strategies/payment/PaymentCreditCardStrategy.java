package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCreditCardStrategy implements PaymentStrategy {

    @Override
    public String checkoutPayment(Payment payment) {
        return String.format("Pagamento via cartão de crédito no valor de %.2f" +
                " foi realizado com sucesso e seu pedido está em preparação", payment.getTotalValue());
    }
}
