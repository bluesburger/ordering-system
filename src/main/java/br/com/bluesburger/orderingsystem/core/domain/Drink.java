package br.com.bluesburger.orderingsystem.core.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Data
public class Drink implements Serializable {
    private BigDecimal value;

    public void applyFivePercentDiscount() {
        BigDecimal discount = value.multiply(BigDecimal.valueOf(0.05));
        value = value.subtract(discount);
    }
}
