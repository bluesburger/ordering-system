package br.com.bluesburger.orderingsystem.core.services;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.core.ports.PaymentPort;
import br.com.bluesburger.orderingsystem.core.services.strategies.payment.PaymentContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentPort paymentPort;

    private final PaymentContext paymentContext;

    public Payment processPayment(Payment payment) {
        var paymentUser = paymentContext.processPayment(payment);
        //fazer validação em cima do retorno do payment para ver se retornou com sucesso
        //na classe payment tem que ter um atrubuto status (stategy)
        //alterar campo com base na validação paymentuser
        //enviar o pedido para fila
        return payment;
    }
}
