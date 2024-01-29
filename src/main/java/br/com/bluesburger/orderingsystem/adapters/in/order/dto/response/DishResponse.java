package br.com.bluesburger.orderingsystem.adapters.in.order.dto.response;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class DishResponse {

    @NonNull
    private final Long id;

    @NonNull
    private final String name;

    @NonNull
    private final String description;

    @NonNull
    private final String category;

    @NonNull
    private final BigDecimal price;
}
