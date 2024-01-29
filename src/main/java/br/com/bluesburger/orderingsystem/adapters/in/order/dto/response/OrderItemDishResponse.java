package br.com.bluesburger.orderingsystem.adapters.in.order.dto.response;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class OrderItemDishResponse {

    @NonNull
    private DishResponse dish;

    @NonNull
    private Integer quantity;

    @NonNull
    private BigDecimal totalAmount;
}
