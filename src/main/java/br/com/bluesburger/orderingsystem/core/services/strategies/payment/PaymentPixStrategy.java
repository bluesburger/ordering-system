package br.com.bluesburger.orderingsystem.core.services.strategies.payment;

import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.ports.out.PaymentMercadoPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPixStrategy implements PaymentStrategy {

    private final PaymentMercadoPagoPort paymentMercadoPagoPort;

    public Payment checkoutPayment(Payment payment) {

        final var qrCodeData = paymentMercadoPagoPort.generatePaymentQRCode(payment);
        payment.completePaymentWithQrCode(qrCodeData);

        return payment;
    }
}
