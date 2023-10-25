package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.core.domain.Payment;

public class PaymentMapper {

    public static PaymentResponse mapperPaymentToPaymentResponse(Payment source) {
        PaymentResponse target = new PaymentResponse();

        target.setTotalAmount(target.getTotalAmount());

        return target;
    }
}
