package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPixStrategy implements PaymentStrategy {

    public String checkoutPayment(Payment payment) {
        return String.format("Pagamento via PIX no valor de %.2f" +
                " foi realizado com sucesso e seu pedido está em preparação", payment.getTotalValue());
    }
}
