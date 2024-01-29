package br.com.bluesburger.orderingsystem.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    private Long id;

    @Setter
    private Integer quantity;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    public void applyTenPercentDiscount() {
        BigDecimal discount = price.multiply(BigDecimal.valueOf(0.1));
        price = price.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }
}
