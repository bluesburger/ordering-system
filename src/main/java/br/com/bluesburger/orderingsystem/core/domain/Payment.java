package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class Payment {

    private final BigDecimal totalValue;
    private final String paymentMethod;
}
