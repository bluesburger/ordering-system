package br.com.bluesburger.orderingsystem.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDrink {

    private Long id;

    private Order order;

    private Drink drink;

    @Setter
    private Integer quantity;

    @Setter
    private BigDecimal totalAmount;
}
