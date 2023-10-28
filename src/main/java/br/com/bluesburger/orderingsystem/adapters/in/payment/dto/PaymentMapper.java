package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public class PaymentMapper {

    public static PaymentResponse mapperPaymentToPaymentResponse(Payment source) {
        return PaymentResponse.builder()
                .message(source.getMessage())
                .build();
    }

    public static Payment mapperPaymentRequestToPayment(PaymentRequest source) {
        return Payment.builder()
                .totalValue(source.getTotalValue())
                .paymentMethod(source.getPaymentMethod())
                .build();
    }
}
