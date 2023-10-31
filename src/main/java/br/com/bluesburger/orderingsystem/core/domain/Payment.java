package br.com.bluesburger.orderingsystem.core.domain;

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
    private String message;
    private Order order;

    public void updateMessagePayment(String message) {
        this.message = message;
    }
}
