package br.com.bluesburger.orderingsystem.adapters.in.order.dto.request;

import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
@Getter
public class DishRequest implements ProductDto, Serializable {

    private static final long serialVersionUID = -2380385291965737284L;

    @NonNull
    private final Long id;

    private final Integer quantity;
}
