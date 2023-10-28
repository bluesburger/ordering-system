package br.com.bluesburger.orderingsystem.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private BigDecimal totalValue;
    private String paymentMethod;
    private String message;

    public void updateMessagePayment(String message) {
        this.message = message;
    }
}
