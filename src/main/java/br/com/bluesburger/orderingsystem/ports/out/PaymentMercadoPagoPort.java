package br.com.bluesburger.orderingsystem.ports.out;

import br.com.bluesburger.orderingsystem.adapters.out.payment.response.PaymentResponseDto;
import br.com.bluesburger.orderingsystem.core.domain.Payment;

public interface PaymentMercadoPagoPort {

    PaymentResponseDto generatePaymentQRCode(Payment payment);
}
