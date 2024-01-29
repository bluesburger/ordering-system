package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.OrderStatus;
import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class PaymentDebitCardStrategy implements PaymentStrategy {

    @Autowired
    private OrderService orderService;

    @Override
    public Payment checkoutPayment(Payment payment) {
        var message = String.format("Pagamento via cartão de debito no valor de %.2f" +
                " foi realizado com sucesso e seu pedido está em preparação", payment.getTotalValue());

        payment.updateMessagePayment(message);
        return payment;
    }
}
