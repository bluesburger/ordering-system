package br.com.bluesburger.orderingsystem.adapters.in.order.dto.response;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class DessertResponse {

    @NonNull
    private final String name;

    @NonNull
    private final String description;

    @NonNull
    private final String category;

    private final Integer quantity;

    @NonNull
    private final BigDecimal price;
}
