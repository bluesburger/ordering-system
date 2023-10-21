package br.com.bluesburger.orderingsystem.adapters.out;

import br.com.bluesburger.orderingsystem.core.ports.PaymentPort;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter implements PaymentPort {
    @Override
    public void sendOrderPreparation() {

    }
}
