package br.com.bluesburger.orderingsystem.core.domain;

import br.com.bluesburger.orderingsystem.adapters.out.payment.response.PaymentResponseDto;
import br.com.bluesburger.orderingsystem.core.services.strategies.payment.PaymentMethodEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private BigDecimal totalValue;

    private PaymentMethodEnum paymentMethod;

    private PaymentStatusEnum paymentStatus;

    @Setter
    private String message;

    private String orderIdQrCode;

    private String qrCode;

    private Order order;

    public void updateMessagePayment(String message) {
        this.message = message;
    }

    public void completeOrder(Order order) {
        this.order = order;
    }

    public void completePaymentWithQrCode(PaymentResponseDto source) {
        this.orderIdQrCode = source.getOrderId();
        this.qrCode = source.getQrData();
    }
}