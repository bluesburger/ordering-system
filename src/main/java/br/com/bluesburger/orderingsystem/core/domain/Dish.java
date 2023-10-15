package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
public class Dish implements Serializable {

    private BigDecimal value;

    public void applyTenPercentDiscount() {
        BigDecimal discount = value.multiply(BigDecimal.valueOf(0.1));
        value = value.subtract(discount);
    }
}
