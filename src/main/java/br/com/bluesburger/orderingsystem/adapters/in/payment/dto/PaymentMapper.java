package br.com.bluesburger.orderingsystem.adapters.in.payment.dto;

import br.com.bluesburger.orderingsystem.core.domain.Order;
import br.com.bluesburger.orderingsystem.core.domain.Payment;
import br.com.bluesburger.orderingsystem.core.domain.PaymentStatusEnum;

import static java.util.Objects.isNull;

public class PaymentMapper {

    private static final String MESSAGE_DEFAULT = "Realize o pagamento atraves do QR Code exibido!";

    public static PaymentResponse mapperPaymentToPaymentResponse(Payment source) {
        return PaymentResponse.builder()
                .status(source.getPaymentStatus().name())
                .message(isNull(source.getMessage()) ? MESSAGE_DEFAULT: source.getMessage())
                .qrCode(source.getQrCode())
                .build();
    }

    public static Payment mapperPaymentRequestToPayment(PaymentRequest source) {
        return Payment.builder()
                .totalValue(source.getTotalValue())
                .paymentMethod(source.getPaymentMethod())
                .paymentStatus(PaymentStatusEnum.PENDING)
                .order(Order.builder().id(source.getOrder().getId()).build())
                .build();
    }
}
