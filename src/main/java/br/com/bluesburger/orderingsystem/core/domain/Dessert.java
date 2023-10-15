package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Data
public class Dessert implements Serializable {
    private BigDecimal value;

    public void applyFifteenPercentDiscount() {
        BigDecimal discount = value.multiply(BigDecimal.valueOf(0.15));
        value = value.subtract(discount);
    }
}
