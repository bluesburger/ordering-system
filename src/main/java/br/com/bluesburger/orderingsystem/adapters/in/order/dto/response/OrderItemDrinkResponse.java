package br.com.bluesburger.orderingsystem.adapters.in.order.dto.response;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class OrderItemDrinkResponse {

    @NonNull
    private DrinkResponse drink;

    @NonNull
    private Integer quantity;

    @NonNull
    private BigDecimal totalAmount;
}
